package com.furniture.store.repository;

import com.furniture.store.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, String> {
    boolean existsByName(String name);
    Optional<Permission> findByName(String name);
}
