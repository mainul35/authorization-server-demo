package com.mainul35.socialloginclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthSuccessHandler authSuccessHandler;
    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> {
                    authorizationManagerRequestMatcherRegistry
                            .requestMatchers("/").permitAll()
                            .anyRequest().authenticated();
                })
                .oauth2Login(httpSecurityOAuth2LoginConfigurer -> {
                    httpSecurityOAuth2LoginConfigurer.successHandler(authSuccessHandler);
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
