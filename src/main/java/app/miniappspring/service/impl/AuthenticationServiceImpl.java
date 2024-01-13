package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.RefreshTokenRequest;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.JWTService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

//    private final UserRepo userRepo;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public CreateUserDto signnup(SignUpRequest signUpRequest){
//        User user= User.builder()
//                .username(signUpRequest.getUsername())
//                .firstname(signUpRequest.getFirstname())
//                .lastname(signUpRequest.getLastname())
//                .roles(Collections.singleton(Role.ROLE_USER))
//                .password(passwordEncoder.encode(signUpRequest.getPassword()))
//                .build();

        CreateUserArgument createUserArgument = userMapper.toCreateUserArgument(signUpRequest);
        return  userService.addUser(createUserArgument);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
//    User user = userRepo.findByUsername(signinRequest.getUsername()).orElseThrow(()->new ErrorException("Пользователь с логином "+signinRequest.getUsername()+" не найден"));
    User user = userService.getByUsername(signinRequest.getUsername());
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
        String username = jwtService.getUserNameFromRefreshToken(refreshTokenRequest.getToken());
//        User user =userRepo.findByUsername(username).orElseThrow(()->new ErrorException("Пользователь с логином "+username+" не найден"));
        User user =userService.getByUsername(username);
        if(jwtService.isTokenValidRefreshToken(refreshTokenRequest.getToken(),user)){
        String newToken =jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse= JwtAuthenticationResponse.builder()
                .token(newToken)
                .refreshToken(refreshTokenRequest.getToken())
                .build();
        return jwtAuthenticationResponse;
    }
    return null;
    }

    @Override
    public User getAuthenticationInfo(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
