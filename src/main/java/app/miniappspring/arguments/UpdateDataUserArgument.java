package app.miniappspring.arguments;

import app.miniappspring.dto.image.UpdateImagerDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateDataUserArgument {
    private String accessToken;
    private String firstname;
    private String lastname;
    private String email;
    private UpdateImagerDto avatar;
}
