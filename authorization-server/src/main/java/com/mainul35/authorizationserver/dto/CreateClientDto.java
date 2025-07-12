package com.mainul35.authorizationserver.dto;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientDto {
    private String clientId;
    private String clientSecret;
    private Set<String> clientAuthenticationMethods;
    private Set<String> authorizationGrantTypes;
    private Set<String> redirectUris;
    private Set<String> scopes;
    private Boolean requireProofKey;
    private Boolean requireAuthorizationConsent;
}
