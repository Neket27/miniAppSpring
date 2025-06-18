package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/nero")
@RequiredArgsConstructor
public class OpenAiController {

    private static final Logger log = LoggerFactory.getLogger(OpenAiController.class);
    private final OpenAiChatModel chatModel;
    private final VectorStore vectorStore;
    private final ObjectMapper objectMapper;
    private final ProductService productService;
    @Value("classpath:/prompts/device.st")
    private Resource sbPromptTemplate;

    static final String INSTRUCTION = """
            ВАЖНО: Ответ верни строго в следующем формате JSON:
            {
              "productIds": [числа],
              "message": "текстовое сообщение"
            }
            Всегда показывай устройства, которые подходят пользователю, если их нет, то так и скажи.
            НЕ добавляй текст вне JSON. НЕ используй Markdown. НЕ объясняй результат.
            """;

    static final String System = """
                Помогайте пользователям в следующих вопросах:
                Поиск датчиков и устройств для "умного дома".
                Совместимость различных датчиков.
                Сборка комплектов устройств.
                Используй информацию об остройствах их его данных: названия, характеристик, описания и тд...
                вызывай функции для получения информации по устройствам.
                """;

    @PostMapping("/chat1")
    public ResponseOpenAi question(@RequestParam String message) {

        PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));
        promptParameters.put("system", System);
        promptParameters.put("instruction", INSTRUCTION);

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .functions(Set.of("getAllCategories", "getProductsByCategory")).build();

        Prompt prompt = promptTemplate.create(promptParameters, options);

        ChatClient chatClient = ChatClient.create(chatModel);
        var content = chatClient.prompt(prompt).call().chatResponse().getResult().getOutput().getText();
        RecomendProductResponse recomendProductResponse;
        try {
            recomendProductResponse = objectMapper.readValue(content, RecomendProductResponse.class);
        }catch (JsonProcessingException e){
            log.error(e.getMessage());
            throw new RuntimeException("Invalid JSON from model");
        }

        List<ProductCardDto> productCardDtos = recomendProductResponse.getProductIds().stream()
                .map(productService::getProductCard)
                .toList();


        ResponseOpenAi responseOpenAi = ResponseOpenAi.builder()
                .productCards(productCardDtos)
                .message(recomendProductResponse.getMessage())
                .build();
        return responseOpenAi;
    }

    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore
                .similaritySearch(SearchRequest.builder().topK(4).query(message).build());
        assert similarDocuments != null;
        return similarDocuments.stream().map(Document::getText).toList();
    }
}