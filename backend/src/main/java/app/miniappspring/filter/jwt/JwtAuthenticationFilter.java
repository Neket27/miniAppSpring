package app.miniappspring.filter.jwt;

import app.miniappspring.service.JWTService;
import app.miniappspring.service.UserService;
import io.opentelemetry.api.internal.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Getter
@Setter
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    @Value("${jwt.secret.prefix}") String prefix;
    private String username;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull(message = "Параметр ответа http фильтра авторизации = null") HttpServletResponse response, @NotNull(message = "Фильтр http авторизации = null ") FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        //TODO было изменение библиотеки StringUtils.isNullOrEmpty, проверить корректность работы
        if (StringUtils.isNullOrEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, prefix )||authHeader.equals(prefix+" null")||authHeader.equals(prefix+" undefined")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authHeader.substring(7);
        username = jwtService.getUserNameFromAccessToken(jwtToken);
        this.username=username;
        RequestContextHolder.currentRequestAttributes().setAttribute("username", username, RequestAttributes.SCOPE_REQUEST);
        if ((!StringUtils.isNullOrEmpty(username)) && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(username);

            if (userDetails!=null && jwtService.isTokenValidAccessToken(jwtToken, userDetails)) { //посмотреть, возможно бесмысленная проверка
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request, response);
    }
}
