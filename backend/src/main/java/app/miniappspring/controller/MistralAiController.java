//package app.miniappspring.controller;
//
//import app.miniappspring.dto.chat.RequestOnAnswerGigaChatDto;
//import app.miniappspring.dto.product.ProductCardDto;
//import app.miniappspring.dto.product.ProductDetailDto;
//import app.miniappspring.service.ProductService;
//import app.miniappspring.service.impl.product.AIProductService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.messages.SystemMessage;
//import org.springframework.ai.chat.messages.UserMessage;
//import org.springframework.ai.chat.model.ChatResponse;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.chat.prompt.PromptTemplate;
//import org.springframework.ai.converter.ListOutputConverter;
//import org.springframework.ai.converter.MapOutputConverter;
//import org.springframework.ai.mistralai.MistralAiChatModel;
//import org.springframework.ai.mistralai.MistralAiChatOptions;
//import org.springframework.core.convert.support.DefaultConversionService;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//@RestController
//@RequestMapping("/api/v1/mistral")
//@RequiredArgsConstructor
//public class MistralAiController {
//
//    private final MistralAiChatModel chatModel;
//    private final ProductService productService;
//    private final AIProductService aiProductService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @PostMapping("/chat1")
//    public ChatResponse getChatCompletion(@RequestParam String prompt) {
//        Prompt p = new Prompt(List.of(new UserMessage(prompt)),
//                MistralAiChatOptions.builder()
//                        .functions(Set.of(
//                                "getAllCategories"
//
//                        ))
//                        .build());
//        return chatModel.call(p);
//    }
//
//    @PostMapping("/chat2")
//    public RequestOnAnswerGigaChatDto getChatCompletion2(@RequestParam String prompt) {
//        String systemMessage = "\"Ты помощник по сайту, который может выполнять действия с помощью вызовов функций. Твоя основная задача — помогать пользователю выбирать товары. \n" +
//                "\n" +
//                "**1. Общие действия:**  \n" +
//                "   - Анализируй запрос пользователя, чтобы понять его потребности.  \n" +
//                "   - Используй информацию о продуктах на сайте (описания, характеристики и протоколы работы) для подбора подходящих товаров.  \n" +
//                "   - Помни контекст разговора, чтобы не запрашивать информацию, которую пользователь уже предоставил.  \n" +
//                "\n" +
//                "**2. Если пользователь просит помочь с выбором устройства:**  \n" +
//                "   - Определи тип устройства, который интересует пользователя (например, \\\"умный выключатель\\\", \\\"хаб\\\", \\\"датчик температуры\\\").  \n" +
//                "   - Проверь совместимость запрашиваемого устройства с другими устройствами, уже установленными у пользователя, если это указано.  \n" +
//                "   - Учитывай возможные сценарии использования устройства, чтобы предложить наиболее подходящий вариант.  \n" +
//                "\n" +
//                "**3. Условия совместимости:**  \n" +
//                "   - Если устройства работают по протоколу Zigbee, они должны быть от одной фирмы. Протокол Zigbee разных фирм несовместим.  \n" +
//                "   - Если устройства работают по протоколу Wi-Fi, они совместимы независимо от фирмы.  \n" +
//                "   - Если тип протокола не указан, предложи уточнить это у пользователя.  \n" +
//                "\n" +
//                "**4. Формат результата:**  \n" +
//                "   - В ответе всегда должны быть перечислены только ID подходящих товаров.  \n" +
//                "   - Пример результата: \\\"1, 5, 8, 54, 67.\\\"  \n" +
//                "   - Если подходящих устройств нет, сообщи об этом и предложи альтернативы, которые требуют минимальных изменений (например, смену хаба).  \n" +
//                "\n" +
//                "**5. Действия после анализа:**  \n" +
//                "   - После анализа совместимости продуктов передай список ID подходящих товаров для дальнейшего использования.  \n" +
//                "   - Если это необходимо, добавь логический комментарий (например, \\\"Эти устройства совместимы с вашим хабом\\\").  \n" +
//                "\n" +
//                "**6. Пример взаимодействия:**  \n" +
//                "   - Пользователь: \\\"Помогите выбрать датчик температуры, который будет работать с моим текущим хабом.\\\"  \n" +
//                "   - Помощник: \\\"Ваш текущий хаб работает по протоколу Zigbee. Совместимые датчики температуры: ID продуктов: 12, 34, 56.\\\"  \n" +
//                "\n" +
//                "**7. Обработка ошибок:**  \n" +
//                "   - Если запрашиваемая информация отсутствует, сообщи об этом и предложи альтернативы.  \n" +
//                "     Пример: \\\"Добавьте информацию о вашем текущем хабе, чтобы я мог помочь вам лучше.\\\"  \n" +
//                "   - Если введённые данные некорректны (например, указано неизвестное устройство), попроси пользователя уточнить информацию.  \n" +
//                "\n" +
//                "**8. Интеграция с функциями сайта:**  \n" +
//                "   - Ты можешь использовать следующие функции:  \n" +
//                "     - Фильтрация товаров по характеристикам.  \n" +
//                "     - Поиск по ключевым словам.  \n" +
//                "     - Сравнение товаров.  \n" +
//                "     - Добавление товара в корзину.  \n" +
//                "\n" +
//                "**9. Пример для работы с корзиной:**  \n" +
//                "   - Пользователь: \\\"Добавьте выбранный датчик температуры в корзину.\\\"  \n" +
//                "   - Помощник: \\\"Датчик температуры 'Датчик 1' (ID: 12) добавлен в корзину. Хотите продолжить покупки или перейти к оформлению заказа?\\\"  \n" +
//                "\n" +
//                "**10. Важные дополнения:**  \n" +
//                "   - Если пользователь запрашивает список товаров, ответ должен быть структурирован и содержать только ID подходящих продуктов.  \n" +
//                "   - Пример ответа: \\\"ID продуктов: 3, 9, 17, 45.\\\"  \n" +
//                "   - Если пользователь хочет получить больше информации, например, описание или цену товаров, уточни это и предложи соответствующие действия.  \n" +
//                "\n" +
//                "**11. Формат ответа:**  \n" +
//                "   - Ответ должен быть минималистичным, только с ID подходящих товаров.  \n" +
//                "   - Если нужно, добавь пояснение: \\\"Эти устройства совместимы с вашим хабом.\\\"  \n" +
//                "\n" +
//                "**12. Дополнительные запросы:**  \n" +
//                "   - Если пользователь уточняет параметры, например, тип протокола или бренд, обнови рекомендации на основе новой информации.  \n" +
//                "   - Уточни, нужно ли сохранить выбранные товары для будущего использования.  \n" +
//                "\n" +
//                "**Цель:**  \n" +
//                "В каждом запросе пользователь должен получить список ID товаров, соответствующих его запросу и условиям совместимости.  \n" +
//                " - Ответ должен быть строго как в примере, без всяких описаний, только перечислить id через запятую: \\\"101,205,303.\\\"  \n" +
//                " - Пример ответа: \\\"101,205,303.\\\"  \n";
//
//
//        systemMessage = "systemMessage = \"\"\"\n" +
//                "**1. Общие действия:**\n" +
//                "   - Анализируй запрос пользователя, чтобы понять его потребности. Уточняй неясные моменты, если это необходимо.\n" +
//                "   - Используй доступную информацию о продуктах на сайте (описания, характеристики, протоколы работы и другие атрибуты), чтобы подобрать подходящие товары.\n" +
//                "\n" +
//                "**2. Выполнение запроса:**\n" +
//                "   - Получай список всех доступных продуктов с помощью вызова функции `getListProduct`.\n" +
//                "   - На основании запроса пользователя фильтруй продукты, анализируя их характеристики и соответствие потребностям.\n" +
//                "\n" +
//                "**3. Формат вывода результата:**\n" +
//                "   - Результат должен содержать **только ID продуктов**, без дополнительных описаний.\n" +
//                "   - ID продуктов перечисляй строго через запятую, без пробелов или других символов.\n" +
//                "   - Если подходящих продуктов нет, верни пустой результат.\n" +
//                "   - **Пример:** 101,102,103\n" +
//                "\n" +
//                "**4. Дополнительные требования:**\n" +
//                "   - Убедись, что все продукты, включённые в результат, удовлетворяют запросу пользователя.\n" +
//                "   - Если запрос пользователя неполный или некорректный, уточняй детали перед обработкой.\n" +
//                "   - Гарантируй оптимизацию обработки запроса для быстрого получения результата.\n" +
//                "\n" +
//                "**5. Обработка ошибок:**\n" +
//                "   - Если функция `getListProduct` возвращает пустой список, запиши сообщение об ошибке в лог и верни пустой результат.\n" +
//                "   - Если возникли проблемы с анализом запроса пользователя (например, отсутствие обязательных параметров), выдай сообщение об ошибке или запроси дополнительные данные.\n" +
//                "\n" +
//                "**6. Дополнительные шаги анализа:**\n" +
//                "   - Учитывай следующие характеристики продуктов при фильтрации:\n" +
//                "     - Совместимость с другими устройствами.\n" +
//                "     - Наличие скидок.\n" +
//                "     - Рейтинг или популярность.\n" +
//                "     - Наличие на складе (поле `stock` > 0).\n" +
//                "   - Применяй фильтры последовательно, чтобы исключить неподходящие продукты.\n" +
//                "\n" +
//                "**7. Логирование процесса:**\n" +
//                "   - Логируй каждый шаг обработки запроса, включая:\n" +
//                "     - Запрос пользователя.\n" +
//                "     - Список продуктов до и после фильтрации.\n" +
//                "     - Итоговый результат (список ID продуктов).\n" +
//                "   - Логи должны содержать временные метки и уровень (информация, ошибка и т. д.).\n" +
//                "\"\"\";\n";
//
//        systemMessage=systemMessage+"\n"+"Description on English:"+
//        "systemMessage = \"\"\"\n" +
//                "**1. General Actions:**\n" +
//                "   - Analyze the user's request to understand their needs. Clarify ambiguous points if necessary.\n" +
//                "   - Use the available product information on the site (descriptions, specifications, protocols, and other attributes) to select suitable products.\n" +
//                "\n" +
//                "**2. Request Execution:**\n" +
//                "   - Retrieve the list of all available products by calling the `getListProduct` function.\n" +
//                "   - Filter products based on the user's request by analyzing their characteristics and matching them with user needs.\n" +
//                "\n" +
//                "**3. Output Format:**\n" +
//                "   - The result must include **only the product IDs**, without additional descriptions.\n" +
//                "   - List product IDs strictly separated by commas, without spaces or other symbols.\n" +
//                "   - If no suitable products are found, return an empty result.\n" +
//                "   - **Example:** 101,102,103\n" +
//                "\n" +
//                "**4. Additional Requirements:**\n" +
//                "   - Ensure that all products included in the result meet the user's request.\n" +
//                "   - If the user's request is incomplete or incorrect, request additional details before processing.\n" +
//                "   - Optimize request handling to quickly deliver results.\n" +
//                "\n" +
//                "**5. Error Handling:**\n" +
//                "   - If the `getListProduct` function returns an empty list, log an error message and return an empty result.\n" +
//                "   - If there are issues with analyzing the user's request (e.g., missing mandatory parameters), provide an error message or request additional information.\n" +
//                "\n" +
//                "**6. Additional Analysis Steps:**\n" +
//                "   - Consider the following product characteristics during filtering:\n" +
//                "     - Compatibility with other devices.\n" +
//                "     - Availability of discounts.\n" +
//                "     - Rating or popularity.\n" +
//                "     - Stock availability (`stock` > 0).\n" +
//                "   - Apply filters sequentially to exclude unsuitable products.\n" +
//                "\n" +
//                "**7. Process Logging:**\n" +
//                "   - Log each step of request processing, including:\n" +
//                "     - The user's request.\n" +
//                "     - The product list before and after filtering.\n" +
//                "     - The final result (list of product IDs).\n" +
//                "   - Logs must include timestamps and levels (information, error, etc.).\n" +
//                "\"\"\";\n";
//
//        String ss= productService.getListProductDetail().stream().peek(e->e.getImageDtoList().clear()).map(ProductDetailDto::toString).toList().toString();
//        String systemM="All products a shop: "+ss+" View only name product, exclusive other words";
//
//        ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());
//        Prompt p = new Prompt(List.of(new SystemMessage(systemMessage+" "+systemM+" "+listOutputConverter.getFormat()), new UserMessage(prompt)),
//                MistralAiChatOptions.builder()
//                        .functions(Set.of(
////                                "getAllCategories",
////                                "getCategoriesAndNumberOfProductsInCategory",
////                                "createCategoryProduct",
////                                "getUserList",
////                                "createUser",
////                                "updateDataUser",
////                                "deleteUserByUsername",
////                                "getListUserRoles",
////                                "getDataUserAboutDelivery",
////                                "addProductInCart",
////                                "removeProductInCart",
////                                "getListProductInCart",
////                                "increaseProductInCart",
////                                "decreaseProductInCart",
////                                "getCountProductInCart",
////                                "signnup",
////                                "signin",
////                                "helloUser",
////                                "resetPassword",
////                                "checkCoupon",
////                                "createCoupon",
////                                "getListCoupon",
////                                "createDiscount",
////                                "checkDiscountAtProduct",
////                                "getDiscountList",
//                                "getListProduct"
////                                "showProductsUsingNeuralNetwork"
//                        ))
//                        .build());
//
////        ListOutputConverter listOutputConverter = new ListOutputConverter(new DefaultConversionService());
//        MapOutputConverter mapOutputConverter = new MapOutputConverter();
//
////        PromptTemplate promptTemplate = new PromptTemplate(p.getContents(), Map.of("promt",p));
//
//        ChatResponse r = chatModel.call(p);
////        String responseAtModel = r.getResult().getOutput().getContent();
////        List<Long> listIdProducts = Stream.of(responseAtModel.split("[^\\d]+")) // Разделяем по всем нечисловым символам
////                .filter(s -> !s.isEmpty())
////                .map(Long::valueOf)
////                .toList();
//
////        Set<ProductCardDto> productCardDtoList = new HashSet<>(aiProductService.showProductsUsingNeuralNetwork(listIdProducts));
////        sendResultSuitableProducts(productCardDtoList);
////        String v= listOutputConverter.convert( r.getResult().getOutput().getContent()).stream().map(s->s.toString()).distinct().dropWhile((s1)->s1.equals("\n")).collect(Collectors.joining(""));
//
//
//        return new RequestOnAnswerGigaChatDto(1L,r.getResult().getOutput().getContent());
//    }
//
//    @MessageMapping("/sendResultSuitableProducts")
//    @SendTo("/resultSuitableProducts/public")
//    public Set<ProductCardDto> sendResultSuitableProducts(Set<ProductCardDto> productCardDtoList) {
//        messagingTemplate.convertAndSend("/resultSuitableProducts/public", productCardDtoList);
//        return productCardDtoList;
//    }
////
////    @Override
////    public void run(ApplicationArguments args) throws Exception {
////        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
////
////        Runnable task_1 = () -> {
////            List<ProductCardDto> productCardDtoList = aiProductService.showProductsUsingNeuralNetwork(List.of(3L));
////            sendResultSuitableProducts(productCardDtoList);
////            System.out.println("Вызыано в " + Calendar.getInstance().getTime());
////        };
////
////
////        executorService.scheduleAtFixedRate(task_1, 0, 1, TimeUnit.SECONDS);
//
////    }
//
//}
