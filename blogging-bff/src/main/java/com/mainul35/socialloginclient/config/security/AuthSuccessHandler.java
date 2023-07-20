package com.mainul35.socialloginclient.config.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;


@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        var accessToken = getAccessToken(authentication);
        String username;
        String imageUrl;
        String name;
        String redirectUrl = "";
        if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("github")) {
            var principal = (DefaultOAuth2User)authentication.getPrincipal();
            var attributes = principal.getAttributes();
            name = attributes.get("name").toString();
            username = attributes.get("login").toString();
            imageUrl = attributes.get("avatar_url").toString();
            redirectUrl = String.format(
                    "http://localhost:4200/?access_token=%s&name=%s&username=%s&image_url=%s&avatar_url=%s",
                    accessToken.getTokenValue(),
                    name, username, imageUrl, imageUrl
                    );

        } else if (((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId().equals("google")){
            var principal = (DefaultOAuth2User)authentication.getPrincipal();
            var attributes = principal.getAttributes();
            name = attributes.get("name").toString();
            username = attributes.get("email").toString().split("@")[0];
            imageUrl = attributes.get("picture").toString();
            redirectUrl = String.format(
                    "http://localhost:4200/?access_token=%s&name=%s&username=%s&image_url=%s&avatar_url=%s",
                    accessToken.getTokenValue(),
                    name, username, imageUrl, imageUrl
            );
        }
        response.sendRedirect(redirectUrl);
    }

    public OAuth2AccessToken getAccessToken (Authentication authentication) {
        var authorizedClient = this.getAuthorizedClient(authentication);
        if (authorizedClient != null) {
            OAuth2AccessToken accessToken = authorizedClient.getAccessToken();
            if (accessToken != null) {
                return accessToken;
            }
        }
        return null;
    }

    private OAuth2AuthorizedClient getAuthorizedClient(Authentication authentication) {
        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
            String principalName = oauthToken.getName();
            return authorizedClientService
                    .loadAuthorizedClient(clientRegistrationId, principalName);
        }
        return null;
    }
}
