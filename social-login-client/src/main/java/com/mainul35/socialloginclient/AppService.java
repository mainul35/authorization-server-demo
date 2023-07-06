package com.mainul35.socialloginclient;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @PreAuthorize("hasAuthority('SCOPE_read')")
    public String getJwtToken() {
        var defaultOidcUser = ((DefaultOidcUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return
                """
                Token = %s
                """.format(defaultOidcUser.getIdToken().getTokenValue());
    }
}
