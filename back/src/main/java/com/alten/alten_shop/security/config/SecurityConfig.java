package com.alten.alten_shop.security.config;

import com.alten.alten_shop.security.filter.CustomAuthenticationFilter;
import com.alten.alten_shop.security.filter.JwtAuthFilter;
import com.alten.alten_shop.security.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;

@Configuration
public class SecurityConfig {
    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomSecurity customSecurity, AuthenticationManager authManager, JwtAuthFilter jwtAuthFilter) throws Exception {
        CustomAuthenticationFilter customAuthFilter = new CustomAuthenticationFilter(authManager, jwtService);
        customAuthFilter.setAuthenticationManager(authManager);

        http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/account", "/token").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products/**").authenticated()
                        .anyRequest().access((authentication, context) -> customSecurity.checkEmail(authentication.get()))
                )
                .addFilterBefore(jwtAuthFilter, CustomAuthenticationFilter.class)
                .addFilter(customAuthFilter);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
