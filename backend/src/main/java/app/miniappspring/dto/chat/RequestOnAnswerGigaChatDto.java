package app.miniappspring.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RequestOnAnswerGigaChatDto {
    private Long userId;
    private String message;
}
