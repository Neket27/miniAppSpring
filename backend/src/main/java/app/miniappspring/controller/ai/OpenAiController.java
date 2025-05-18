package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.service.ProductService;
import lombok.RequiredArgsConstructor;
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

import java.util.*;

@RestController
@RequestMapping("/api/v1/nero")
@RequiredArgsConstructor
public class OpenAiController {

    private final OpenAiChatModel chatModel;
    private final VectorStore vectorStore;
    @Value("classpath:/prompts/device.st")
    private Resource sbPromptTemplate;

    private final ProductService productService;

    @PostMapping("/chat1")
    public ResponseOpenAi question(@RequestParam String message) {

        String system = """
                Помогайте пользователям в следующих вопросах:
                Поиск датчиков и устройств для "умного дома".
                Совместимость различных датчиков.
                Сборка комплектов устройств.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));
        promptParameters.put("system", system);
        promptParameters.put("instruction", """
                В начале ответа до символа | помещай id товаров (пиши их через запятую), которые подошли,
                а после подробно напиши сообщения почему ты выбрал эти товары.
                Пример: 1,5,7|твой подробный ответ почему эти товары.
                """);

        OpenAiChatOptions options = OpenAiChatOptions.builder().functions(Set.of("getAllCategories", "getProductsByCategory")).build();
        Prompt prompt = promptTemplate.create(promptParameters, options);

        String response = chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();

        String[] idProductsAndMessage = response.split("\\|");

        List<Long> idProducts = Arrays.stream(idProductsAndMessage[0].split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::valueOf)
                .toList();

        List<ProductCardDto> productCardDtos = idProducts.stream()
                .map(productService::getProductCard)
                .toList();

        ResponseOpenAi responseOpenAi = ResponseOpenAi.builder()
                .productCards(productCardDtos)
                .message(idProductsAndMessage[1])
                .build();

        return responseOpenAi;
    }


    @PostMapping("/filtred-products")
    public ResponseOpenAi question2(@RequestParam String message) {
        PromptTemplate promptTemplate = new PromptTemplate(sbPromptTemplate);

        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("input", message);
        promptParameters.put("documents", String.join("\n", findSimilarDocuments(message)));
        promptParameters.put("instruction", """
                Ты ассистент, который возвращает ТОЛЬКО id продуктов через символ '|', 
                "без описаний и других слов. Пример, формат ответа: id1|id2|id3".
                После всех id, в этой же строке, пишешь символ & без пробелов и 
                дальше пишешь сообщение-рекомендацию пользователю уже соблюдая все правила пунктуации, точки пробелы и тд..
                """
        );

        promptParameters.put("system", "Когда ты получаешь товары, то в объекте есть поле название(name), заметок(note), бренд(brand) анализируй информацию с этих полей и отвечай на вопрос пользователя");

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .functions(Set.of("getAllCategories", "getProductsByCategory")).build();
        Prompt prompt = promptTemplate.create(promptParameters, options);


        String response = chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText()
                .trim();
        String[] idProductsAndRecommendMessage;
        try {

            idProductsAndRecommendMessage = response.split("&");

            List<Long> idProducts = Arrays.stream(idProductsAndRecommendMessage[0].split("\\|"))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::valueOf)
                    .toList();

            String recommendMessage = idProductsAndRecommendMessage[1];

            List<ProductCardDto> productCardDtos = idProducts.stream()
                    .map(productService::getProductCard)
                    .toList();
            return new ResponseOpenAi(recommendMessage, productCardDtos);
//        return new ResponseOpenAi("kkkk",productService.getListCardProduct());
        } catch (Exception e) {
            return new ResponseOpenAi(response, List.of());
        }
    }


    private List<String> findSimilarDocuments(String message) {
        List<Document> similarDocuments = vectorStore
                .similaritySearch(SearchRequest.builder().topK(3).query(message).build());
        assert similarDocuments != null;
        return similarDocuments.stream().map(Document::getText).toList();
    }


//    public ChatResponse getChatCompletion(@RequestParam String prompt) {
//        Prompt p = new Prompt(List.of(new UserMessage(prompt)),
//                OpenAiChatOptions.builder()
//                        .functions(Set.of(
//                                "getAllCategories"
//
//                        ))
//                        .build());
//
//        return chatModel.call(p);
//    }
}
