package com.mainul35.authorizationserver.service;

import com.mainul35.authorizationserver.dto.CreateAppUserDto;
import com.mainul35.authorizationserver.dto.MessageDto;
import com.mainul35.authorizationserver.entity.AppUser;
import com.mainul35.authorizationserver.entity.Role;
import com.mainul35.authorizationserver.enums.RoleName;
import com.mainul35.authorizationserver.repository.AppUserRepository;
import com.mainul35.authorizationserver.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public MessageDto createUser(CreateAppUserDto dto) {
        AppUser appUser = AppUser.builder()
                .username(dto.username())
                .password(passwordEncoder.encode(dto.password()))
                .build();
        Set<Role> roles = new HashSet<>();
        dto.roles().forEach(r -> {
            Role role = roleRepository.findByRole(RoleName.valueOf(r)).orElseThrow(() -> new RuntimeException("Role not found"));
            roles.add(role);
        });
        appUser.setRoles(roles);
        appUserRepository.save(appUser);
        return new MessageDto("User " + appUser.getUsername() + " saved");
    }
}
