package app.miniappspring.filter;



import app.miniappspring.dto.jwtToken.JwtAuthentication;
import app.miniappspring.entity.User;
import app.miniappspring.exception.ErrorException;
import app.miniappspring.repository.UserRepo;
import app.miniappspring.service.MyUserDetailsService;
import app.miniappspring.utils.jwtToken.JwtProvider;
import app.miniappspring.utils.jwtToken.JwtUtils;
import app.miniappspring.utils.jwtToken.LoadUser;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
@Setter
@Getter
public class JwtFilter  extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;
    private final UserRepo userRepo;
//    private final MyUserDetailsService myUserDetailsService;
//    private UserDetailsService userDetailsService;
    private final LoadUser loadUser;
//    private final AuthenticationManager authManager;
private final AuthenticationConfiguration authConfiguration;
@Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        User user = userRepo.findByUsername("u1").orElseThrow(() -> new ErrorException("Пользователь с логином " + " не найден"));
        final String token = getTokenFromRequest((HttpServletRequest) request);
        if (token != null && jwtProvider.validateAccessToken(token)) {
            filterChain.doFilter(request,response);
            final Claims claims = jwtProvider.getAccessClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims, user);
            jwtInfoToken.setAuthenticated(true);

//            JwtAuthentication myUserDetails =
//                     loadUser
//                    .loadUserByUsername(user.getUsername());

//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    myUserDetails,
//                    null,
//                    myUserDetails.getAuthorities()
//            );

            UserDetails userDetails = loadUser.loadUserByUsername(user.getUsername());
                    SecurityContext securityContext= SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            securityContext.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(securityContext);

        }
        filterChain.doFilter(request, response);
    }



    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}