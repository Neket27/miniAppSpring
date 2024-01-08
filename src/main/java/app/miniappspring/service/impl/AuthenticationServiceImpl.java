package app.miniappspring.service.impl;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.RefreshTokenRequest;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public User signnup(SignUpRequest signUpRequest){
        User user= User.builder()
                .username(signUpRequest.getUsername())
                .firstname(signUpRequest.getFirstname())
                .lastname(signUpRequest.getLastname())
                .roles(Collections.singleton(Role.ROLE_USER))
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
        return userRepo.save(user);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
    User user = userRepo.findByUsername(signinRequest.getUsername()).orElseThrow(()->new ErrorException("Пользователь с логином "+signinRequest.getUsername()+" не найден"));
    String token =jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
    JwtAuthenticationResponse jwtAuthenticationResponse= JwtAuthenticationResponse.builder()
            .token(token)
            .refreshToken(refreshToken)
            .build();
    return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user =userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
    if(jwtService.isTokenValid(refreshTokenRequest.getToken(),user)){
        String newToken =jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse= JwtAuthenticationResponse.builder()
                .token(newToken)
                .refreshToken(refreshTokenRequest.getToken())
                .build();
        return jwtAuthenticationResponse;
    }
    return null;
    }

}
