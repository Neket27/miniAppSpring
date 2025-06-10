package app.miniappspring.controller.ai;

import app.miniappspring.dto.product.ProductCardDto;
import app.miniappspring.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final ObjectMapper objectMapper;
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
               Формат ответа:
               {
                  "productIds": [6, 4, 8],
                  "message": "Вот устройства Tuya: умные розетки и датчик температуры. Обратите внимание, что устройства Tuya работают через приложения Tuya или Smart Life и не совместимы с Aqara."
               }
                """);

        OpenAiChatOptions options = OpenAiChatOptions.builder().functions(Set.of("getAllCategories", "getProductsByCategory")).build();
        Prompt prompt = promptTemplate.create(promptParameters, options);

        String response = chatModel.call(prompt)
                .getResult()
                .getOutput()
                .getText();

        RecomendProductResponse recomendProductResponse = objectMapper.convertValue(response, RecomendProductResponse.class);

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
                .similaritySearch(SearchRequest.builder().topK(3).query(message).build());
        assert similarDocuments != null;
        return similarDocuments.stream().map(Document::getText).toList();
    }
}