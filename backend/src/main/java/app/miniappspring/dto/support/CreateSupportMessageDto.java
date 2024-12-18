package app.miniappspring.dto.support;

import app.miniappspring.dto.image.CreateImageDto;
import app.miniappspring.dto.image.ImageDto;
import lombok.Data;

import java.util.List;

@Data
public class CreateSupportMessageDto {
    String nameUser;
    String email;
    String message;
    List<CreateImageDto> imageDtoList;
}
