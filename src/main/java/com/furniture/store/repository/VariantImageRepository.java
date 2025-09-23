package com.furniture.store.repository;

import com.furniture.store.entity.VariantImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantImageRepository extends JpaRepository<VariantImage, Long> {
}