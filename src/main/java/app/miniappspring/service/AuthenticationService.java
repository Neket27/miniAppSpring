package app.miniappspring.service;

import app.miniappspring.dto.jwtToken.*;
import app.miniappspring.dto.user.CreateUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService {
    CreateUserDto signnup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(String token);
    UserDetails getAuthenticationInfo();
    void logout(String refreshToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException;
}
