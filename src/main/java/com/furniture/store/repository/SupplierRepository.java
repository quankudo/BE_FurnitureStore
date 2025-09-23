package com.furniture.store.repository;

import com.furniture.store.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsBySupplierName(String supplierName);
}