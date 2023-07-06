package com.mainul35.socialloginclient;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {

    @GetMapping("/")
    public ResponseEntity<String> getPublicData() {
        return ResponseEntity.ok("Public data");
    }

    @PreAuthorize("hasAnyAuthority('SCOPE_profile')")
    @GetMapping("/private-data")
    public ResponseEntity<String> getPrivateData() {
        var defaultOidcUser = ((DefaultOidcUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var tokenString =
                """
                Token = %s
                """.format(defaultOidcUser.getIdToken().getTokenValue());
        return ResponseEntity.ok(tokenString);
    }
}
