package app.miniappspring.service;

import app.miniappspring.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

public interface JWTService {

    String generateToken(UserDetails userDetails);

    String extractUserName(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

    String generateRefreshToken(HashMap<String, Object> extraClaims, UserDetails userDetails);


}
