package app.miniappspring.controller;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.RefreshTokenRequest;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.User;
import app.miniappspring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
