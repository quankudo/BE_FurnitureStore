package com.furniture.store.repository;

import com.furniture.store.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByName(String name);
    Optional<Role> findByName(String name);
}
