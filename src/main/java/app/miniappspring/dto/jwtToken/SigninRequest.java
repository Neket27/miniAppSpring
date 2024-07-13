package app.miniappspring.dto.jwtToken;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SigninRequest {
    private String username;
    private String password;
}
