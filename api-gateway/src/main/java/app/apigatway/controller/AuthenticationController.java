package app.apigatway.controller;

import app.apigatway.dto.jwtToken.JwtAuthenticationResponse;
import app.apigatway.dto.jwtToken.ResetPasswordDto;
import app.apigatway.dto.jwtToken.SignUpRequest;
import app.apigatway.dto.jwtToken.SigninRequest;
import app.apigatway.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/signup")
    public Mono<ResponseEntity<JwtAuthenticationResponse>> signup(@RequestBody Mono<SignUpRequest> signUpRequestMono) {
        return signUpRequestMono.flatMap(signUpRequest ->
                authenticationService.signup(signUpRequest)
                        .map(ResponseEntity::ok)
        );
    }

    @PostMapping("/signin")
    public Mono<ResponseEntity<JwtAuthenticationResponse>> signin(@RequestBody Mono<SigninRequest> signinRequestMono) {
        return signinRequestMono.flatMap(signinRequest ->
                authenticationService.signin(signinRequest)
                        .map(jwtAuthenticationResponse -> ResponseEntity.ok(jwtAuthenticationResponse))
                        .defaultIfEmpty(ResponseEntity.status(403).build())
        );
    }

    @GetMapping("/refresh")
    public Mono<JwtAuthenticationResponse> refresh(@CookieValue(value = "refreshToken", required = false) String refreshToken) {
        return authenticationService.refreshToken(refreshToken);
    }

    @PostMapping("/logout")
    public Mono<ResponseEntity<String>> logout(@CookieValue(value = "refreshToken") String refreshTokenFromCookie) {
        return authenticationService.logout(refreshTokenFromCookie)
                .thenReturn(ResponseEntity.ok("Вы вышли из системы"));
    }

    @PostMapping("/password/reset")
    public Mono<ResponseEntity<JwtAuthenticationResponse>> resetPassword(@RequestBody Mono<ResetPasswordDto> resetPasswordDtoMono) {
        return resetPasswordDtoMono.flatMap(resetPasswordDto ->
                authenticationService.resetPassword(resetPasswordDto)
                        .map(ResponseEntity::ok)
        );
    }
}
