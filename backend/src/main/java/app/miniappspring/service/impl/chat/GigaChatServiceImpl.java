package app.miniappspring.service.impl.chat;

import app.miniappspring.dto.chat.*;
import app.miniappspring.service.GigaChatService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

@Service
public class GigaChatServiceImpl implements GigaChatService {

    private WebClient webClient;
    private final String API_URL_MESSAGE = "https://gigachat.devices.sberbank.ru/api/v1/chat/completions";
    private final String API_URL_AUTH = "https://ngw.devices.sberbank.ru:9443/api/v2/oauth";
    @Value("${gigachat.scope}")
    private String SCOPE;
    @Value("${gigachat.authorization.key}")
    private String authorizationKey;
    private AuthResponseGigaChat authResponseGigaChat;

    public GigaChatServiceImpl() {
        try {
            this.webClient = createClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    @Scope("prototype")
    private WebClient createClient() {
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> {
                    try {
                        sslContextSpec.sslContext(SslContextBuilder.forClient()
                                .trustManager(InsecureTrustManagerFactory.INSTANCE) //Отключение проверки сертификата
                                .build());
                    } catch (SSLException e) {
                        throw new RuntimeException(e);
                    }
                });


        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter((request, next) -> {
                    System.out.println("Request: " + request.method() + " " + request.url());
                    request.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ":" + value)));
                    return next.exchange(request)
                            .doOnNext(response -> {
                                System.out.println("Response Status: " + response.statusCode());
                                response.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
                            });
                })
                .baseUrl("https://ngw.devices.sberbank.ru:9443/api/v2/oauth")
                .build();

    }

    @Override
    public RequestOnAnswerGigaChatDto getResponseFromGigaChat(RequestOnAnswerGigaChatDto requestOnAnswerDto) {
        if (authResponseGigaChat == null)
            auth();
        MessagesGigaChat messagesGigaChat = new MessagesGigaChat("user", requestOnAnswerDto.getMessage());
        DtoGigaChat dtoGigaChat = new DtoGigaChat("GigaChat", List.of(messagesGigaChat), false);
        String responseFromGigaChat;
        ChatCompletionResponse response = null;

        String content = "";
        try {
            responseFromGigaChat = webClient
                    .post()
                    .uri(API_URL_MESSAGE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(dtoGigaChat)
                    .header("Authorization", "Bearer " + authResponseGigaChat.getAccessToken())
                    .header("Content-Type", "application/json")
                    .retrieve()
//                .onStatus(, response -> {
//                    return Mono.error(new RuntimeException("Server GigaChat Error"));
//                })
                    .bodyToMono(String.class)
                    .block();


            ObjectMapper objectMapper = new ObjectMapper();

            // Парсим JSON строку в дерево узлов
            JsonNode rootNode = objectMapper.readTree(responseFromGigaChat);

            // Получаем массив choices
            JsonNode choicesArray = rootNode.path("choices");

            for (JsonNode choice : choicesArray) {
                // Получаем сообщение
                JsonNode messageNode = choice.path("message");
                content = messageNode.path("content").asText();
                String role = messageNode.path("role").asText();

                System.out.println("Content: " + content);
                System.out.println("Role: " + role);

                int index = choice.path("index").asInt();
                String finishReason = choice.path("finish_reason").asText();

                System.out.println("Index: " + index);
                System.out.println("Finish Reason: " + finishReason);
            }

            long created = rootNode.path("created").asLong();
            String model = rootNode.path("model").asText();
            String objectType = rootNode.path("object").asText();

            System.out.println("Created: " + created);
            System.out.println("Model: " + model);
            System.out.println("Object Type: " + objectType);

            // Получаем объект usage
            JsonNode usageNode = rootNode.path("usage");
            int promptTokens = usageNode.path("prompt_tokens").asInt();
            int completionTokens = usageNode.path("completion_tokens").asInt();
            int totalTokens = usageNode.path("total_tokens").asInt();

            System.out.println("Prompt Tokens: " + promptTokens);
            System.out.println("Completion Tokens: " + completionTokens);
            System.out.println("Total Tokens: " + totalTokens);


        } catch (WebClientResponseException webClientResponseException) {
            if (webClientResponseException.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                auth();
                getResponseFromGigaChat(requestOnAnswerDto);
            } else
                webClientResponseException.printStackTrace();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


        return RequestOnAnswerGigaChatDto.builder()
                .userId(requestOnAnswerDto.getUserId())
                .message(content)
                .build();
    }

    private void auth() {
        try {
            String body = "scope=" + URLEncoder.encode(SCOPE, StandardCharsets.UTF_8);
            // Генерация RqUID (уникального идентификатора запроса)
            String rqUID = UUID.randomUUID().toString();
            authResponseGigaChat = webClient
                    .post()
                    .uri(API_URL_AUTH)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)  // Устанавливаем правильный тип контента
                    .header("Authorization", "Bearer " + authorizationKey)
                    .header("Accept", "")  // Заголовок Accept
                    .header("RqUID", rqUID)  // Пример дополнительного заголовка
                    .bodyValue(body)  // Передаем тело запроса
                    .retrieve()  // Получаем ответ
                    .bodyToMono(AuthResponseGigaChat.class)  // Преобразуем тело ответа в строку
                    .block();  // Получаем результат синхронно
            System.out.println(authResponseGigaChat);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}