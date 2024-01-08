package app.miniappspring.dto.jwtToken;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtAuthenticationResponse {
    private String token;
    private  String refreshToken;
}
