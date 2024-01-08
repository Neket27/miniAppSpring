package app.miniappspring.service;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.RefreshTokenRequest;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.entity.User;

public interface AuthenticationService {
    User signnup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
