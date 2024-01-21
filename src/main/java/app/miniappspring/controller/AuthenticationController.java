package app.miniappspring.controller;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.ResetPasswordDto;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/singup")
    public ResponseEntity<JwtAuthenticationResponse> singup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signnup(signUpRequest));

    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest) {
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

//    @GetMapping("/refresh")
//    public ResponseEntity<JwtAuthenticationResponse>refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
//        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest.getToken()));
//    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@CookieValue(value = "refreshToken") String refreshToken) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue(value = "refreshToken") String refreshTokenFromCooke, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
        authenticationService.logout(refreshTokenFromCooke, httpServletRequest, httpServletResponse);
        return ResponseEntity.ok("Вы вышли из системы");
    }

    @PostMapping("resetPassword")
    public ResponseEntity<JwtAuthenticationResponse> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        return ResponseEntity.ok(authenticationService.resetPassword(resetPasswordDto));
    }

}
