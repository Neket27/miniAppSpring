package app.apigatway.service.impl;


import app.apigatway.arguments.CreateUserArgument;
import app.apigatway.dto.cooke.CreateCookeDto;
import app.apigatway.dto.jwtToken.JwtAuthenticationResponse;
import app.apigatway.dto.jwtToken.ResetPasswordDto;
import app.apigatway.dto.jwtToken.SignUpRequest;
import app.apigatway.dto.jwtToken.SigninRequest;
import app.apigatway.dto.user.UpdateUserDto;
import app.apigatway.entity.User;
import app.apigatway.exception.ErrorException;
import app.apigatway.security.AuthenticationManager;
import app.apigatway.service.AuthenticationService;
import app.apigatway.service.CookeService;
import app.apigatway.service.JWTService;
import app.apigatway.service.UserService;
import app.apigatway.utils.jwtToken.EncoderPassword;
import app.apigatway.utils.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    public Mono<JwtAuthenticationResponse> signup(SignUpRequest signUpRequest) {
        return userService.getByUsername(signUpRequest.getUsername())
                .flatMap(user -> signin(new SigninRequest(signUpRequest.getUsername(), signUpRequest.getPassword())))
                .switchIfEmpty(
                        Mono.defer(() -> {
                            CreateUserArgument createUserArgument = userMapper.toCreateUserArgument(signUpRequest);
                            return userService.addUser(createUserArgument)
                                    .then(signin(new SigninRequest(signUpRequest.getUsername(), signUpRequest.getPassword())));
                        })
                );
    }

    @Override
    public Mono<JwtAuthenticationResponse> signin(SigninRequest signinRequest) {
        return Mono.fromRunnable(() ->
                        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                signinRequest.getUsername(),
                                signinRequest.getPassword()
                        ))
                )
                .then(userService.getByUsername(signinRequest.getUsername()))
                .flatMap(user -> user == null
                        ? Mono.empty()
                        : Mono.just(createJwtAuthenticationResponse(user))
                );
    }

    @Override
    public Mono<JwtAuthenticationResponse> refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return Mono.just(JwtAuthenticationResponse.builder().build());
        }

        return Mono.just(refreshToken)
                .flatMap(token -> {
                    String username = jwtService.getUserNameFromRefreshToken(token);
                    return userService.getByUsername(username)
                            .filter(user -> jwtService.isTokenValidRefreshToken(token, user) &&
                                    jwtService.getRefreshToken(token) != null)
                            .map(user -> {
                                createCooke(token);
                                return createJwtAuthenticationResponse(user);
                            });
                })
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<User> getAuthenticationInfo() {
        return ReactiveSecurityContextHolder.getContext()
                .map(securityContext -> (User) securityContext.getAuthentication().getPrincipal());
    }

    @Override
    public Mono<Void> logout(String refreshToken) {
        return Mono.defer(() -> {
            jwtService.removeRefreshToken(refreshToken);
            return Mono.empty();
        });
    }

    @Override
    public Mono<JwtAuthenticationResponse> resetPassword(ResetPasswordDto resetPasswordDto) {
        return getAuthenticationInfo()
                .flatMap(authUser -> {
                    if (EncoderPassword.equalsPasswords(resetPasswordDto.getPassword(), authUser.getPassword())) {
                        authUser.setPassword(EncoderPassword.encode(resetPasswordDto.getNewPassword()));

                        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                                .username(authUser.getUsername())
                                .password(resetPasswordDto.getNewPassword())
                                .roles(authUser.getRoles())
                                .build();

                        return userService.updateDataUser(updateUserDto)
                                .then(Mono.fromRunnable(() -> authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                authUser.getUsername(),
                                                resetPasswordDto.getNewPassword())
                                )))
                                .thenReturn(createJwtAuthenticationResponse(authUser));
                    } else {
                        return Mono.error(new ErrorException("Отправленный пароль и пароль авторизированного пользователя не совпадают. Изменение пароля не произошло"));
                    }
                });
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
