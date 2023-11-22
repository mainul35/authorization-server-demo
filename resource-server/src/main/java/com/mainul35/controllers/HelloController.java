package com.mainul35.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        LocalDateTime time = LocalDateTime.now();
        return "Hello from the resource server! - " + time;
    }

}
