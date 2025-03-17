//package app.miniappspring.service.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.chat.prompt.Prompt;
//import org.springframework.ai.mistralai.MistralAiChatModel;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class MistralAiService {
//
//    private final MistralAiChatModel mistralAi;
////    private final GigachatChatModel gigachat;
//
//    public void response(String message){
////        String prompt = "What is the statuses of the following payment transactions 003, 001, 002? Use multiple funciotn calls if needed.";
////        prompt="Привет, отвечай на русском, как тебя зовут?";
//
////            System.out.println("\n MISTRAL AI (Streaming): " + content(mistralAi.stream(new Prompt(prompt))) + "\n");
//        System.out.println("\n MISTRAL AI: " + mistralAi.call(message) + "\n");
//    };
//
//    public void response(Prompt prompt){
////        String prompt = "What is the statuses of the following payment transactions 003, 001, 002? Use multiple funciotn calls if needed.";
////        prompt="Привет, отвечай на русском, как тебя зовут?";
//
////            System.out.println("\n MISTRAL AI (Streaming): " + content(mistralAi.stream(new Prompt(prompt))) + "\n");
//        System.out.println("\n MISTRAL AI: " + mistralAi.call(prompt) + "\n");
//    };
//
//}
//
//
////package app.miniappspring.service.impl;
////
////
////import org.springframework.ai.mistralai.MistralAiChatModel;
////import org.springframework.ai.mistralai.MistralAiChatOptions;
////import org.springframework.ai.chat.model.ChatResponse;
////import org.springframework.ai.chat.prompt.Prompt;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////@Service
////public class MistralAiService {
////
////    @Autowired
////    private MistralAiChatModel chatModel;
////
////    public ChatResponse getChatCompletion(String promptText) {
////        Prompt prompt = new Prompt(promptText);
////        return chatModel.call(prompt);
////    }
////}
