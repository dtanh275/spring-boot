package dev.thejavaman.springboot.oauth2authorizationserver.jwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.thejavaman.springboot.oauth2authorizationserver.jwt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
