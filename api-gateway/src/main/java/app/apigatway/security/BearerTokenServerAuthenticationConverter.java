package app.apigatway.security;

import app.apigatway.service.JWTService;
import app.apigatway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class BearerTokenServerAuthenticationConverter implements ServerAuthenticationConverter {

    private final JWTService jwtService;
    private final UserService userService;

    private static final String BEARER_PREFIX = "Bearer ";
    private static final Function<String, Mono<String>> getBearerValue = authValue -> Mono.justOrEmpty(authValue.substring(BEARER_PREFIX.length()));

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        Mono<String> jwtToken = extractHeader(exchange);
        return jwtToken
                .flatMap(token -> jwtService.getUserNameFromAccessToken(token)
                        .flatMap(nameUser -> userService.findByUsername(nameUser)
                                .flatMap(userDetails -> jwtService.isTokenValidAccessToken(token, userDetails)
                                        .filter(Boolean::booleanValue) // filter true results
                                        .switchIfEmpty(Mono.error(new AuthenticationCredentialsNotFoundException("Token Invalid")))
                                        .flatMap(isValid -> UserAuthenticationBearer.create(userDetails)) //Create authentication on success
                                )
                        )
                )
                .onErrorResume(e -> Mono.empty());

    }




    private Mono<String> extractHeader(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION));
    }
}