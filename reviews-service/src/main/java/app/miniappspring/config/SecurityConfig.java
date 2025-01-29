package app.miniappspring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

//    private final JwtAuthenticationFilter jwtAuthenticationFilter;
//    private final UserService userService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


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
//
//
//
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
//                        .requestMatchers("/hi/**").permitAll()  // Путь доступен для всех
//                        .requestMatchers("/hi/**").hasAuthority("SCOPE_resource.read")  // Но только для пользователей с нужным правом
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }



//    @Bean
//    public AuthenticationProvider authenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        UserDetailsService userDetailsService = userService.getUserDetailsService();
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


}