package com.spring.spring_rest.repository;


import com.spring.spring_rest.entity.Auth_User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Spring Data JPA repository for managing {@link Auth_User} entities.
 */
public interface AuthUserRepository extends JpaRepository<Auth_User, Long> {
    Optional<Auth_User> findByUsername(String theUsername);
    Boolean existsByUsername(String theUsername);
} 


