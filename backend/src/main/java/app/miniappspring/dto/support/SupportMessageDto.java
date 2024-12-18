package app.miniappspring.dto.support;

import app.miniappspring.dto.image.ImageDto;
import lombok.Data;

import java.util.List;
@Data
public class SupportMessageDto {
    private Long id;
    private String nameUser;
    private String email;
    private String message;
    private List<ImageDto> imageDtoList;
}
