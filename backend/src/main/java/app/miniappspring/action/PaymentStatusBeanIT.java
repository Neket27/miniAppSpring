package app.miniappspring.action;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
class PaymentStatusBeanIT {

    private final MistralAiChatModel  chatModel;
//    private final GigaChatService gigaChatService;


    private static final Map<Config.Transaction2, Config.Status2> DATASET = Map.of(
            new Config.Transaction2("001"), new Config.Status2("pending"),
            new Config.Transaction2("002"), new Config.Status2("approved"),
            new Config.Transaction2("003"), new Config.Status2("rejected"));


//    @Bean
//    ApplicationRunner applicationRunner(MistralAiChatModel mistralAi) {
//
//        return args -> {
//            System.out.println("kk");
//            callStateTransaction();
//        };
//    }

    public void callStateTransaction() {

                    String prompt = "What is the statuses of the following payment transactions 003, 001, 002? Use multiple funciotn calls if needed.";
                    prompt = "Каковы статусы следующих платежных транзакций 003, 001, 002? При необходимости используйте несколько вызовов функций.";

                    Prompt l = new Prompt(List.of(new UserMessage(prompt)),
                            MistralAiChatOptions.builder()
                                    .function("paymentStatus")
                                    .build());

                    Prompt p = new Prompt(prompt, MistralAiChatOptions
                            .builder()
                            .function("paymentStatus")
                            .functions(Set.of("paymentStatus"))
                            .build()
                    );

                    ChatResponse response = chatModel.call(l);

        String prompt2 = "Каковы статусы следующих платежных транзакций 003, 001, 002? При необходимости используйте несколько вызовов функций.";

//        String response = gigaChatService.getResponseFromGigaChat(new RequestOnAnswerGigaChatDto(1L,prompt2)).getMessage();
                    System.out.println("p = "+response);
    }


    @Configuration
    static class Config {

        @Bean
        @Description("Get the status of a payment transaction")
        public Function<Transaction2, Status2> paymentStatus() {
            return transaction -> DATASET.get(transaction);
        }

        record Transaction2(@JsonProperty String id) {
        }

        record Status2(@JsonProperty String name) {
        }

    }

}