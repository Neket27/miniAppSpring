package app.miniappspring.dto.chat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ChatCompletionResponse {
    private List<Choice> choices;
    private long created;
    private String model;
    private String object;
    private Usage usage;


    @Data
    public static class Choice {
        private Message message;
        private int index;
        private String finishReason;
    }

    @Data
    public static class Message {
        private String content;
        private String role;
    }

    @Data
    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;
    }
}