package org.lessons.java.travels_space.repository;

import java.util.Optional;

import org.lessons.java.travels_space.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
