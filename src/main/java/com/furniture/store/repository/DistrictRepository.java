package com.furniture.store.repository;

import com.furniture.store.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);
    List<District> findByNameContaining(String name);
}