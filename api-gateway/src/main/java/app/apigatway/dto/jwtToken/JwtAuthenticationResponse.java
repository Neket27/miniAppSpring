package app.apigatway.dto.jwtToken;


import app.apigatway.dto.user.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class JwtAuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private UserDto user;
}
