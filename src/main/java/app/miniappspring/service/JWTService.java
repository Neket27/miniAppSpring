package app.miniappspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.HashMap;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String generateRefreshToken(HashMap<String, UserDetails> extraClaims, UserDetails userDetails);

    String getUserNameFromAccessToken(String token);

    String getUserNameFromRefreshToken(String token);

    String extractUserName(String token, SecretKey secretKey);

    boolean isTokenValidAccessToken(String token, UserDetails userDetails);

    boolean isTokenValidRefreshToken(String token, UserDetails userDetails);
}
