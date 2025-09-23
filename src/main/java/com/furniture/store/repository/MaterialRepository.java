package com.furniture.store.repository;

import com.furniture.store.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    boolean existsByMaterialName(String materialName);
}
