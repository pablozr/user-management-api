package com.bagaggio.user_management.repository;

import com.bagaggio.user_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDisplayName(String username);
    Optional<User> findByEmail(String email);
}
