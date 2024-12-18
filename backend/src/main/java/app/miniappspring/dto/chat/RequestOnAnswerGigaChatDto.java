package app.miniappspring.dto.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestOnAnswerGigaChatDto {
    private Long userId;
    private String message;
}
