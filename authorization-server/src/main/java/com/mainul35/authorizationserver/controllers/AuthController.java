package com.mainul35.authorizationserver.controllers;

import com.mainul35.authorizationserver.dto.CreateAppUserDto;
import com.mainul35.authorizationserver.dto.MessageDto;
import com.mainul35.authorizationserver.entity.Role;
import com.mainul35.authorizationserver.enums.RoleName;
import com.mainul35.authorizationserver.repository.RoleRepository;
import com.mainul35.authorizationserver.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${init.key}")
    private String initKey;

    private final AppUserService appUserService;

    private final RoleRepository roleRepository;

    @GetMapping("/initialize")
    public ResponseEntity<MessageDto> init (@RequestParam(name = "key", required = true) String key) {
        if (key.isBlank()) {
            throw new RuntimeException("Key must not be empty");
        }

        if (!key.equals(initKey)) {
            throw new RuntimeException("Invalid key provided");
        }

        populateDefaultRoles();
        return ResponseEntity.ok(new MessageDto("Populated default roles"));
    }

    private void populateDefaultRoles () {
		var roleAdmin = Role.builder().role(RoleName.ROLE_ADMIN).build();
		var roleUser = Role.builder().role(RoleName.ROLE_USER).build();
		roleRepository.save(roleAdmin);
		roleRepository.save(roleUser);
    }

    @PostMapping("/create-user")
    public ResponseEntity<MessageDto> createUser (@RequestBody CreateAppUserDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(dto));
    }
}
