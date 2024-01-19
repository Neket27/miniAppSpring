package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.jwtToken.*;
import app.miniappspring.dto.user.CreateUserDto;
import app.miniappspring.entity.User;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.JWTService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public CreateUserDto signnup(SignUpRequest signUpRequest){
        CreateUserArgument createUserArgument = userMapper.toCreateUserArgument(signUpRequest);
        return  userService.addUser(createUserArgument);
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest, HttpServletResponse httpServletResponse){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(),signinRequest.getPassword()));
    User user = userService.getByUsername(signinRequest.getUsername());
    String token =jwtService.generateToken(user);
    String refreshToken = jwtService.generateRefreshToken(new HashMap<>(),user);
    jwtService.saveToken(user,refreshToken);

        Cookie cookie = new Cookie("refreshToken", refreshToken);//создаем объект Cookie,
        //в конструкторе указываем значения для name и value
        cookie.setPath("/");//устанавливаем путь
        cookie.setMaxAge(86400);//здесь устанавливается время жизни куки
        httpServletResponse.addCookie(cookie);//добавляем Cookie в запрос
        httpServletResponse.setContentType("text/plain");//устанавливаем контекст


    JwtAuthenticationResponse jwtAuthenticationResponse= JwtAuthenticationResponse.builder()
            .accessToken(token)
            .refreshToken(refreshToken)
            .build();
    return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String username = jwtService.getUserNameFromRefreshToken(refreshTokenRequest.getToken());
        User user =userService.getByUsername(username);
        if(jwtService.isTokenValidRefreshToken(refreshTokenRequest.getToken(),user)){
        String newToken =jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse= JwtAuthenticationResponse.builder()
                .accessToken(newToken)
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

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
        Cookie cookie = new Cookie("refreshToken","");//создаем объект Cookie,
        //в конструкторе указываем значения для name и value
        cookie.setPath("/");//устанавливаем путь
        cookie.setMaxAge(0);//здесь устанавливается время жизни куки
        httpServletResponse.addCookie(cookie);//добавляем Cookie в запрос
        httpServletResponse.setContentType("text/plain");//устанавливаем контекст
        httpServletRequest.logout();

    }

}
