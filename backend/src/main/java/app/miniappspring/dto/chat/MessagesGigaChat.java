package app.miniappspring.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessagesGigaChat {
    private String role;
    private String content;
}
