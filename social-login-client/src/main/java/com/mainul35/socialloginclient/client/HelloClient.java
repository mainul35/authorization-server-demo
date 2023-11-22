package com.mainul35.socialloginclient.client;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange("http://localhost:8090")
public interface HelloClient {

    @GetExchange("/")
    String getHello();
}

