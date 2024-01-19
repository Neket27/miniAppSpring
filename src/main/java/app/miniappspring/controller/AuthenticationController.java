package app.miniappspring.controller;

import app.miniappspring.dto.jwtToken.*;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.service.AuthenticationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/singup")
    public ResponseEntity<CreateUserDto> singup(@RequestBody SignUpRequest signUpRequest){
return ResponseEntity.ok(authenticationService.signnup(signUpRequest));

    }

    @PostMapping("/signin")
    public JwtAuthenticationResponse signin(@RequestBody SigninRequest signinRequest, HttpServletResponse httpServletResponse){
        return authenticationService.signin(signinRequest,httpServletResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
        authenticationService.logout(httpServletRequest,httpServletResponse);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
