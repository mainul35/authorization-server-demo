package com.mainul35.authorizationserver.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GoogleUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String photoUrl;

    public static GoogleUser fromOauth2User (OAuth2User oAuth2User) {
        GoogleUser googleUser = GoogleUser.builder()
                .email(oAuth2User.getAttribute("email"))
                .name(oAuth2User.getAttribute("name"))
                .givenName(oAuth2User.getAttribute("given_name"))
                .familyName(oAuth2User.getAttribute("family_name"))
                .photoUrl(oAuth2User.getAttribute("picture"))
                .build();
        return googleUser;
    }
}
