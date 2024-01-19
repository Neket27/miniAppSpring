package app.miniappspring.dto.jwtToken;

import app.miniappspring.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtAuthenticationResponse {
    private String accessToken;
    private  String refreshToken;
    private User user;
}
