package com.furniture.store.repository;

import com.furniture.store.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    boolean existsByNameAndSlug(String name, String slug);

    Optional<Category> findBySlug(String slug);
    List<Category> findByNameContaining(String name);
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
