package com.mainul35.socialloginclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class BloggingBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingBffApplication.class, args);
	}

}
