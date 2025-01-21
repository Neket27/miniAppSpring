package app.miniappspring.service.impl.authention;

import app.miniappspring.dto.jwtToken.JwtAuthenticationResponse;
import app.miniappspring.dto.jwtToken.ResetPasswordDto;
import app.miniappspring.dto.jwtToken.SignUpRequest;
import app.miniappspring.dto.jwtToken.SigninRequest;
import app.miniappspring.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class AIAuthenticationService {

    private final AuthenticationService authenticationService;

    @Bean
    @Description("The function of registration on the website")
    public Function<RequestSignup,JwtAuthenticationResponse> signnup(){
        return requestSignup -> authenticationService.signnup(requestSignup.signUpRequest());
    }

    @Bean
    @Description("The authorization function on the website")
    public Function<RequestSignin,JwtAuthenticationResponse> signin(){
        return requestSignin -> authenticationService.signin(requestSignin.signInRequest());
    }

    @Bean
    @Description("hell user")
    public Supplier<String> helloUser(){
        return ()->authenticationService.getAuthenticationInfo().getUsername();
    }

//    @Bean
//    @Description("")
//    public Consumer<> logout(){
//
//    }

    @Bean
    @Description("Function reset password")
    public Function<RequestResetPassword, JwtAuthenticationResponse> resetPassword(){
        return requestResetPassword -> authenticationService.resetPassword(requestResetPassword.resetPasswordDto());
    }



}

record RequestSignup(SignUpRequest signUpRequest){}
record RequestSignin(SigninRequest signInRequest){}
//record RequestLogout(String )
record RequestResetPassword(ResetPasswordDto resetPasswordDto){}

