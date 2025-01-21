package app.miniappspring.service.impl;

import app.miniappspring.dto.chat.RequestOnAnswerGigaChatDto;
import app.miniappspring.service.GigaChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GigachatChatModel implements ChatModel {

    private final GigaChatService gigaChatService;

    @Override
    public String call(String message) {
        String m = gigaChatService.getResponseFromGigaChat(new RequestOnAnswerGigaChatDto(1L,message)).getMessage();
        return ChatModel.super.call(m);
    }

    @Override
    public String call(Message... messages) {
        return ChatModel.super.call(messages);
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        String m = gigaChatService.getResponseFromGigaChat(new RequestOnAnswerGigaChatDto(1L,prompt.getContents())).getMessage();
        Generation generation = new Generation(new AssistantMessage(m));
        List<Generation> generations =List.of(generation);
        return new ChatResponse(generations);
    }

    @Override
    public ChatOptions getDefaultOptions() {
        return ChatModel.super.getDefaultOptions();
    }

    @Override
    public Flux<String> stream(String message) {
        return ChatModel.super.stream(message);
    }

    @Override
    public Flux<String> stream(Message... messages) {
        return ChatModel.super.stream(messages);
    }

    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {
        return ChatModel.super.stream(prompt);
    }
}