package app.miniappspring.dto.user;

import app.miniappspring.dto.image.ImageDto;
import app.miniappspring.dto.image.UpdateImagerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateDataUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private UpdateImagerDto avatar;
}