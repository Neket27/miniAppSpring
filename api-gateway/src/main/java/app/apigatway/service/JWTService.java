package app.apigatway.service;

import app.apigatway.entity.TokenJWT;
import app.apigatway.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.HashMap;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(HashMap<String, UserDetails> extraClaims, UserDetails userDetails);

    TokenJWT saveToken(User user, String refreshToken);

    Mono<String> getUserNameFromAccessToken(String token);

    String getUserNameFromRefreshToken(String token);

    String extractUserName(String token, SecretKey secretKey);

    Mono<Boolean> isTokenValidAccessToken(String token, UserDetails userDetails);

    boolean isTokenValidRefreshToken(String token, UserDetails userDetails);

    @Transactional
    TokenJWT getRefreshToken(String refreshToken);

    void removeRefreshToken(String refreshToken);
}
