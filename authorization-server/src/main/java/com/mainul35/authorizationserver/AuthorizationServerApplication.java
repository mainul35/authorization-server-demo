package com.mainul35.authorizationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class AuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}
}
