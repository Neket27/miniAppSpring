package app.apigatway.security;

import app.apigatway.entity.User;
import app.apigatway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final UserService userService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
       CustomPrincipal customPrincipal =  (CustomPrincipal) authentication.getPrincipal();
        return userService.getById(customPrincipal.getId())
                .filter(User::isEnabled)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found")))
                .map(user -> authentication);
    }
}
