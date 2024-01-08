package app.miniappspring.config;

import app.miniappspring.filter.JwtFilter;
import app.miniappspring.service.MyUserDetailsService;
import app.miniappspring.utils.jwtToken.LoadUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {

    private final JwtFilter jwtFilter;
//    private final AuthenticationConfiguration authConfiguration;
//    private final LoadUser loadUser;

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html"
    };

//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return authConfiguration.getAuthenticationManager();
//    }

//    @Autowired
//    public void configure(AuthenticationManagerBuilder builder, AuthenticationProvider jwtAuthenticationProvider) {
//        builder.authenticationProvider(jwtAuthenticationProvider);
//    }

    @Bean
    public UserDetailsService userDetailsService(){
        return jwtFilter.getLoadUser();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // убрать потом
                .cors(AbstractHttpConfigurer::disable) // убрать потом
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(swssion->swssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("home").permitAll()
//                     //   .requestMatchers("login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
//              //  .httpBasic(withDefaults());

                //   .csrf(AbstractHttpConfigurer::disable) // убрать потом
                .authorizeHttpRequests((authz) -> authz
                                //  .requestMatchers("home","registration").permitAll()
                                //.requestMatchers("main").authenticated()
                                // .requestMatchers("filter").authenticated()
//                         .requestMatchers("registration").permitAll()
                                .requestMatchers(AUTH_WHITELIST).permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().permitAll()

                )
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(login->{login
//                            .loginPage("/login.html")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true);
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(withDefaults());


        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}