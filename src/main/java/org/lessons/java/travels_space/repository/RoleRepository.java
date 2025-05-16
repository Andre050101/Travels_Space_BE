package org.lessons.java.travels_space.repository;

import java.util.Optional;

import org.lessons.java.travels_space.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
