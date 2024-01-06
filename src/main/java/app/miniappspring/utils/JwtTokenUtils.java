package app.miniappspring.utils;

import app.miniappspring.detail.MyUserDetails;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
//@ComponentScan("app.miniappspring.utils")
public class JwtTokenUtils {
//    @Value("${jwt.secret}")
    private String secret="DFGHJ444DFHJG777LKJHMMM";
//    @Value("${jwt.lifetime}")
    private Duration jwtLifetime= Duration.ofMinutes(30);
    public  String generateToken(User user){
        Map<String,Object> claims = new HashMap<>();
        List<Role> rolesList = user.getRoles().stream().toList();
        List<String>rolesListString= rolesList.stream().map(Enum::name).toList();
//                .stream().map(Enum::name).toList();

        claims.put("roles",rolesListString);
        claims.put("active",user.isActive());
        Date issuedDate =new Date();
        Date expiredDate = new Date(issuedDate.getTime()+jwtLifetime.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJwt(token)
                .getBody();
    }

    public String getUsername(String token){
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles",List.class);
    }

}
