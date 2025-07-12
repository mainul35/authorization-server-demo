package com.mainul35.authorizationserver.repository;

import com.mainul35.authorizationserver.entity.Role;
import com.mainul35.authorizationserver.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleName roleName);
}
