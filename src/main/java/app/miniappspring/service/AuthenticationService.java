package app.miniappspring.service;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.RefreshTokenRequest;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    CreateUserDto signnup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    UserDetails getAuthenticationInfo();
}
