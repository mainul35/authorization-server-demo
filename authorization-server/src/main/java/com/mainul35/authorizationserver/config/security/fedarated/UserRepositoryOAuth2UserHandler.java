package com.mainul35.authorizationserver.config.security.fedarated;

// tag::imports[]

import com.mainul35.authorizationserver.entity.GoogleUser;
import com.mainul35.authorizationserver.repository.GoogleUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Objects;
import java.util.function.Consumer;
// end::imports[]

/**
 * Example {@link Consumer} to perform JIT provisioning of an {@link OAuth2User}.
 *
 * @author Steve Riesenberg
 * @since 1.1
 */
// tag::class[]
@Slf4j
@RequiredArgsConstructor
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final GoogleUserRepository googleUserRepository;

    @Override
    public void accept(OAuth2User user) {
        // Capture user in a local data store on first authentication
        if (user.getAttribute("iss").toString().contains("google") && Objects.nonNull(user.getAttribute("email"))) {
            if (!this.googleUserRepository.findByEmail(user.getAttribute("email")).isPresent()) {
                GoogleUser googleUser = GoogleUser.fromOauth2User(user);
                log.info("Saving first-time user: name=" + user.getName() + ", claims=" + user.getAttributes() + ", authorities=" + user.getAuthorities());
                this.googleUserRepository.save(googleUser);
            } else {
                this.googleUserRepository.findByEmail(user.getAttribute("email"));
            }
        } else {
            log.error("Couldn't find google user");
        }
    }


}
// end::class[]