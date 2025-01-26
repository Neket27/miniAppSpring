package app.apigatway.config;

import app.apigatway.security.AuthenticationManager;
import app.apigatway.security.BearerTokenServerAuthenticationConverter;
import app.apigatway.service.JWTService;
import app.apigatway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;
    @Autowired
    private JWTService jwtService;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, AuthenticationManager authenticationManager) {
        // @formatter:off
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
//                .authorizeExchange(exchanges -> exchanges
////                .pathMatchers("/","/api/","/**", "/auth/register", "/auth/login").permitAll() // Allow these patterns
//                                .pathMatchers("/","/api/","/**", "/auth/register", "/auth/login", "/api/v1/auth/signup").permitAll()
//                                .anyExchange().authenticated()  // Require authentication for all other requests
//        )

                .authorizeExchange(exchanges -> exchanges
                        .anyExchange().permitAll() // Разрешить все запросы без аутентификации
                );

//                .addFilterAt(bearerAuthenticationFilter(authenticationManager), SecurityWebFiltersOrder.AUTHENTICATION);
        // @formatter:on
        return http.build();
    }


    private AuthenticationWebFilter bearerAuthenticationFilter(AuthenticationManager authenticationManager) {
        AuthenticationWebFilter bearerAuthenticationFilter = new AuthenticationWebFilter(authenticationManager);
        bearerAuthenticationFilter.setServerAuthenticationConverter(new BearerTokenServerAuthenticationConverter(jwtService, userService));
        bearerAuthenticationFilter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/secure/**"));

        return bearerAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

//import app.apigatway.entity.Role;
//import app.apigatway.jwt.JwtAuthenticationFilter;
//import app.apigatway.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig  {
//
//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final UserService userService;
//
//    private static final String[] AUTH_WHITELIST = {
//            "/api/v1/auth/**",
//            "/v3/api-docs/**",
//            "/v3/api-docs.yaml",
//            "/swagger-ui/**",
//            "/swagger-ui.html"
//    };
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable) // убрать потом
//                .authorizeHttpRequests(request->request
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .requestMatchers("/api/v1/auth/**")
//                                .permitAll()
//                        .requestMatchers("/api/v1/admin").hasAnyAuthority(Role.ROLE_ADMIN.name())
//                              //  .requestMatchers("/api/v1/user").hasAnyAuthority(Role.ROLE_USER.name())
//                        .anyRequest().permitAll())
//                .sessionManagement(manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
////
////                .formLogin(login->{login
//////                            .loginPage("/login.html")
////                        .loginProcessingUrl("/login")
////                        .defaultSuccessUrl("/home", true);
////                })
//
//        return http.build();
//    }
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        UserDetailsService userDetailsService = userService.getUserDetailsService().block();
//        if(userDetailsService != null) {
//            provider.setUserDetailsService(userDetailsService);
//            provider.setPasswordEncoder(passwordEncoder());
//        }
//        return provider;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//
//}