package app.miniappspring.dto.user;

import app.miniappspring.dto.image.UpdateImageDto;
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
    private UpdateImageDto avatar;
}