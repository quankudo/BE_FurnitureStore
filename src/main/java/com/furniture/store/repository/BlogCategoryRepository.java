package com.furniture.store.repository;

import com.furniture.store.entity.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {
    boolean existsByName(String name);
}
