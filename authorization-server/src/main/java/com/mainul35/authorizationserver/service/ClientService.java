package com.mainul35.authorizationserver.service;

import com.mainul35.authorizationserver.dto.CreateClientDto;
import com.mainul35.authorizationserver.dto.MessageDto;
import com.mainul35.authorizationserver.entity.Client;
import com.mainul35.authorizationserver.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    private Client clientFromDto (CreateClientDto clientDto) {
        return Client.builder()
                .clientId(clientDto.getClientId())
                .clientSecret(passwordEncoder.encode(clientDto.getClientSecret()))
                .clientAuthenticationMethods(clientDto.getClientAuthenticationMethods())
                .authorizationGrantTypes(clientDto.getAuthorizationGrantTypes())
                .redirectUris(clientDto.getRedirectUris())
                .scopes(clientDto.getScopes())
                .requireProofKey(clientDto.getRequireProofKey())
                .requireAuthorizationConsent(clientDto.getRequireAuthorizationConsent())
                .build();
    }

    @Override
    public void save(RegisteredClient registeredClient) {
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    public MessageDto create (CreateClientDto clientDto) {
        Client client = clientFromDto(clientDto);
        clientRepository.save(client);
        return new MessageDto("Client " + client.getClientId() + " created successfully");
    }
}
