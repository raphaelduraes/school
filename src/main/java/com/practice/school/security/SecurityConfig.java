package com.practice.school.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return  http.csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(authorization -> {
                    authorization
                            .requestMatchers((HttpMethod.GET)).permitAll()
                            .requestMatchers(HttpMethod.POST).hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                            .requestMatchers(HttpMethod.PUT).hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(HttpMethod.DELETE).hasAnyAuthority("ROLE_ADMIN")
                    .anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
               .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
