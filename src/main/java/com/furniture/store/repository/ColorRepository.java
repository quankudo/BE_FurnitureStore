package com.furniture.store.repository;

import com.furniture.store.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    boolean existsByColorNameAndColorValue(String colorName, String colorValue);
}