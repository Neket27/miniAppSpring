package app.miniappspring.service.impl;

import app.miniappspring.arguments.CreateUserArgument;
import app.miniappspring.dto.cooke.CreateCookeDto;
import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.ResetPasswordDto;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.dto.user.UpdateUserDto;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.service.AuthenticationService;
import app.miniappspring.service.CookeService;
import app.miniappspring.service.JWTService;
import app.miniappspring.service.UserService;
import app.miniappspring.utils.jwtToken.EncoderPassword;
import app.miniappspring.utils.mapper.UserMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    private final CookeService cookeService;
    @Value("${cooke.time.live.refreshToken}")
    private int cookeTimeLive;

    @Override
    public JwtAuthenticationResponse signnup(SignUpRequest signUpRequest) {

        User user = userService.getByUsername(signUpRequest.getUsername());//проверим что пользователь существует, можно проверить через user == null
        if (user != null)
            signin(new SigninRequest(signUpRequest.getUsername(), signUpRequest.getPassword()));

        CreateUserArgument createUserArgument = userMapper.toCreateUserArgument(signUpRequest);
        userService.addUser(createUserArgument);
        return signin(new SigninRequest(signUpRequest.getUsername(), signUpRequest.getPassword()));

    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        User user = userService.getByUsername(signinRequest.getUsername());
        if (user == null)
            return null;

        return createJwtAuthenticationResponse(user);
    }

    @Override
    public JwtAuthenticationResponse refreshToken(String refreshToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
        if (refreshToken == null || refreshToken.isEmpty())
            return JwtAuthenticationResponse.builder().build();

        String username = jwtService.getUserNameFromRefreshToken(refreshToken);
        User user = userService.getByUsername(username);
        if (jwtService.isTokenValidRefreshToken(refreshToken, user) && jwtService.getRefreshToken(refreshToken) != null) {
            createCooke(refreshToken);
            return createJwtAuthenticationResponse(user);
        }

//        else {logout(refreshToken,httpServletRequest,httpServletResponse);    }
        return null;
    }

    @Override
    public User getAuthenticationInfo() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public void logout(String refreshToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException {
        httpServletRequest.logout();
        jwtService.removeRefreshToken(refreshToken);
    }

    @Override
    public JwtAuthenticationResponse resetPassword(ResetPasswordDto resetPasswordDto) {
        User authenticationUser = getAuthenticationInfo();
        if (EncoderPassword.equalsPasswords(resetPasswordDto.getPassword(), authenticationUser.getPassword())) {
            authenticationUser.setPassword(EncoderPassword.encode(resetPasswordDto.getNewPassword()));

            UpdateUserDto updateUserDto = UpdateUserDto.builder()
                    .username(authenticationUser.getUsername())
                    .password(resetPasswordDto.getNewPassword())
                    .roles(authenticationUser.getRoles())
                    .build();

            userService.updateDataUser(updateUserDto);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationUser.getUsername(), resetPasswordDto.getNewPassword()));
        } else {
            throw new ErrorException("Отправленный пароль и пароль авторизированного пользователя не совпадают. Изменение пароля не произошло");
        }
        return createJwtAuthenticationResponse(authenticationUser);
    }

    private JwtAuthenticationResponse createJwtAuthenticationResponse(User user) {
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        jwtService.saveToken(user, refreshToken);

        createCooke(refreshToken);

        return JwtAuthenticationResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .user(userMapper.toUserDto(user))
                .build();
    }

    private void createCooke(String refreshToken) {
        CreateCookeDto createCookeDto = CreateCookeDto.builder()
                .key("refreshToken")
                .data(refreshToken)
                .timeLiveCooke(cookeTimeLive)
                .path("/")
                .contentType("text/plain")
                .build();
        cookeService.createCooke(createCookeDto);
    }

}
