package app.miniappspring.utils.jwtToken;


import app.miniappspring.dto.jwtToken.JwtAuthentication;
import app.miniappspring.entity.Role;
import app.miniappspring.entity.User;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class JwtUtils  {

    public static JwtAuthentication generate(Claims claims,User user) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication(user);
//        Object v = jwtInfoToken.getPrincipal();
        jwtInfoToken.setRoles(getRoles(claims));////////////////////////////////////////////////////////////////////////////////////////
//        jwtInfoToken.getUser().setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}
