package app.apigatway.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UserAuthenticationBearer {
    public static Mono<Authentication> create(UserDetails userDetails) {
        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
    }
}