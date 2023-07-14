package com.mainul35.socialloginclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Public data");
    }

    @GetMapping("/private-data")
    public ResponseEntity<String> getPrivateData() {
        return ResponseEntity.ok(appService.getJwtToken());
    }
}
