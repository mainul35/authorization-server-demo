package com.mainul35.authorizationserver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String clientSecret;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "client_authentication_methods", columnDefinition = "text")
    private Set<String> clientAuthenticationMethods;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "client_authentication_methods", columnDefinition = "text")
    private Set<String> authorizationGrantTypes;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> redirectUris;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> scopes;
    private Boolean requireProofKey;
    private Boolean requireAuthorizationConsent;

    public static RegisteredClient toRegisteredClient (Client client) {
        RegisteredClient.Builder builder = RegisteredClient.withId(client.getClientId())
                .clientId(client.getClientId())
                .clientSecret(client.getClientSecret())
                .clientIdIssuedAt(LocalDateTime.now().toInstant(ZoneOffset.UTC))
                .clientAuthenticationMethods(m -> m.addAll(client.getClientAuthenticationMethods().stream().map(ClientAuthenticationMethod::valueOf).collect(Collectors.toSet())))
                .authorizationGrantTypes(agt -> agt.addAll(client.getAuthorizationGrantTypes().stream().map(AuthorizationGrantType::new).collect(Collectors.toSet())))
                .redirectUris(ru -> ru.addAll(client.getRedirectUris()))
                .scopes(s -> s.addAll(client.getScopes()))
                .clientSettings(
                        ClientSettings.builder()
                                .requireAuthorizationConsent(client.getRequireAuthorizationConsent())
                                .requireProofKey(client.getRequireProofKey())
                                .build());
        return builder.build();
    }
}
