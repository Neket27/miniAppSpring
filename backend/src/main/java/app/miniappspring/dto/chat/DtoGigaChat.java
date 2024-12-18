package app.miniappspring.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DtoGigaChat {
    private String model;
    private List<MessagesGigaChat> messages;
    private Boolean stream;
}
