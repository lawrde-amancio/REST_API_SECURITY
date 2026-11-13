package com.spring.spring_rest.repository;

import com.spring.spring_rest.entity.Auth_Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring Data JPA repository for managing {@link Auth_Role} entities.
 */
public interface AuthRoleRepository extends JpaRepository<Auth_Role, Long> {
    Optional<Auth_Role> findByName(String theName);
    Boolean existsByName(String theUsername);
}
