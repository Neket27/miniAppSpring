package com.example.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request->request
                        .requestMatchers("/resource/**")
                        .permitAll()

                        .requestMatchers("/api/v1/admin").hasAnyAuthority("hasAuthority('SCOPE_resource.read')")
                        .anyRequest().permitAll())
                .oauth2ResourceServer()
                .jwt();




        return http.build();
    }

}