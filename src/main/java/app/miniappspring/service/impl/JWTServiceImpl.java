package app.miniappspring.service.impl;

import app.miniappspring.entity.TokenJWT;
import app.miniappspring.entity.User;
import app.miniappspring.repository.TokenRepo;
import app.miniappspring.service.JWTService;
import app.miniappspring.service.UserService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service

//@RequiredArgsConstructor
@NoArgsConstructor
@Slf4j
@Setter
@Getter

public class JWTServiceImpl implements JWTService {

//    @Value("${jwt.secret.access}")
//    private SecretKey jwtAccessSecret;
//    @Value("${jwt.secret.refresh}")
//    private SecretKey jwtRefreshSecret;
    @Autowired
    private  TokenRepo tokenRepo;
    @Autowired
    private  UserService userService;

    private SecretKey jwtAccessSecret=null;
    private SecretKey jwtRefreshSecret=null;
    public void createSecretKeys(@Value("${jwt.secret.access}") String jwtAccessSecret, @Value("${jwt.secret.refresh}") String jwtRefreshSecret) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));

    }



    public String generateToken(@NonNull UserDetails userDetails){
        createSecretKeys("zL1HB3Pch05Avfynovxrf/kpF9O2m4NCWKJUjEp27s9J2jEG3ifiKCGylaZ8fDeoONSTJP/wAzKawB8F9rOMNg==","zL1HB3Pch05Avfynovxrf/kpF9O2m4NCWKJUjEp27s9J2jEG3ifiKCGylaZ8fDeoONSTJP/wAzKawB8F9rOMNg==");
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()*1000*60*24))
                .signWith(jwtAccessSecret, SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public String generateRefreshToken(@NonNull HashMap<String, UserDetails> extraClaims, @NonNull UserDetails userDetails) {
        createSecretKeys("zL1HB3Pch05Avfynovxrf/kpF9O2m4NCWKJUjEp27s9J2jEG3ifiKCGylaZ8fDeoONSTJP/wAzKawB8F9rOMNg==","zL1HB3Pch05Avfynovxrf/kpF9O2m4NCWKJUjEp27s9J2jEG3ifiKCGylaZ8fDeoONSTJP/wAzKawB8F9rOMNg==");
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(jwtRefreshSecret, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public TokenJWT saveToken(User user, String refreshToken){
        TokenJWT tokenJWT = tokenRepo.findById(user.getId()).orElse(null);
        if(tokenJWT!=null){
            tokenJWT.setRefreshToken(refreshToken);
            return tokenRepo.save(tokenJWT);
        }
        return tokenRepo.save(new TokenJWT(user.getId(),refreshToken,user));

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
