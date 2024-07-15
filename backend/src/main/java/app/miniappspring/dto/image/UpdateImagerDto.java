package app.miniappspring.dto.image;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateImagerDto {
    private String name;
    private String contentType;
    private String base64;
}
