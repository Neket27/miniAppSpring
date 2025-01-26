package app.apigatway.arguments;


import app.apigatway.dto.image.UpdateImageDto;
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
    private UpdateImageDto avatar;
}
