package app.miniappspring.dto.image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateImageDto {
    private String name;
    private String contentType;
    private String base64;

}
