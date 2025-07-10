package com.mainul35.authorizationserver.controllers;

import com.mainul35.authorizationserver.dto.CreateClientDto;
import com.mainul35.authorizationserver.dto.MessageDto;
import com.mainul35.authorizationserver.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<MessageDto> create (@RequestBody CreateClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(clientDto));
    }
}
