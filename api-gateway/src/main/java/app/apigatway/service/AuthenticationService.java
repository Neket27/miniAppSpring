package app.apigatway.service;

import app.apigatway.dto.jwtToken.JwtAuthenticationResponse;
import app.apigatway.dto.jwtToken.ResetPasswordDto;
import app.apigatway.dto.jwtToken.SignUpRequest;
import app.apigatway.dto.jwtToken.SigninRequest;
import app.apigatway.entity.User;
import reactor.core.publisher.Mono;

public interface AuthenticationService {
    Mono<JwtAuthenticationResponse> signup(SignUpRequest signUpRequest);

    Mono<JwtAuthenticationResponse> signin(SigninRequest signinRequest);

    Mono<JwtAuthenticationResponse> refreshToken(String token);

    Mono<User> getAuthenticationInfo();

    Mono<Void> logout(String refreshToken);

    Mono<JwtAuthenticationResponse> resetPassword(ResetPasswordDto resetPasswordDto);
}