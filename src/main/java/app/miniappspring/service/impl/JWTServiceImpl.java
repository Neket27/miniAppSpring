package app.miniappspring.service.impl;

import app.miniappspring.service.JWTService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Slf4j
@Service
public class JWTServiceImpl implements JWTService {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    public JWTServiceImpl(@Value("${jwt.secret.access}") String jwtAccessSecret, @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }


    public String generateToken(@NonNull UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(jwtAccessSecret, SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public String generateRefreshToken(@NonNull HashMap<String, UserDetails> extraClaims, @NonNull UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(jwtRefreshSecret, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String getUserNameFromAccessToken(@NonNull String token) {
        return extractClaim(token,jwtAccessSecret,Claims::getSubject);
    }

    @Override
    public String getUserNameFromRefreshToken(@NonNull String token){
        return extractClaim(token,jwtRefreshSecret,Claims::getSubject);
    }

    @Override
    public String extractUserName(@NonNull String token, @NonNull SecretKey secretKey) {
        return extractClaim(token,secretKey,Claims::getSubject);
    }

    private <T> T extractClaim(@NonNull String token,@NonNull SecretKey secretKey, Function<Claims,T> claimsResolvers){
        final Claims claims= getClaims(token,secretKey);
        return claimsResolvers.apply(claims);
    }

    private Claims getClaims(@NonNull String token, @NonNull SecretKey secretKey) {
        try {
          return   Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
      catch (
    ExpiredJwtException expEx) {
        log.error("Token expired", expEx);
    } catch (
    UnsupportedJwtException unsEx) {
        log.error("Unsupported jwt", unsEx);
    } catch (MalformedJwtException mjEx) {
        log.error("Malformed jwt", mjEx);
    } catch (
    SignatureException sEx) {
        log.error("Invalid signature", sEx);
    } catch (Exception e) {
        log.error("invalid token", e);
    }
        return null;
    }

    @Override
    public boolean isTokenValidAccessToken(@NonNull String token, @NonNull UserDetails userDetails){
        final String username =getUserNameFromAccessToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token,jwtAccessSecret));
    }

    @Override
    public boolean isTokenValidRefreshToken(@NonNull String token, @NonNull UserDetails userDetails){
        final String username =getUserNameFromRefreshToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token,jwtRefreshSecret));
    }

    private boolean isTokenExpired(@NonNull String token,@NonNull SecretKey secretKey) {
      return  extractClaim(token,secretKey,Claims::getExpiration).before(new Date());
    }


}
