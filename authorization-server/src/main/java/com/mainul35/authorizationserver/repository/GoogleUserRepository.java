package com.mainul35.authorizationserver.repository;

import com.mainul35.authorizationserver.entity.GoogleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoogleUserRepository extends JpaRepository<GoogleUser, Long> {

    Optional<GoogleUser> findByEmail(String email);
}
