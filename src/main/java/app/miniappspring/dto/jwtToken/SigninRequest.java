package app.miniappspring.dto.jwtToken;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SigninRequest {
    private String username;
    private String password;
}
