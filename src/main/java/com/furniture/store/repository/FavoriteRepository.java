package com.furniture.store.repository;

import com.furniture.store.entity.Favorite;
import com.furniture.store.entity.Product;
import com.furniture.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    boolean existsByUserAndProduct(User user, Product product);
    void deleteByUserAndProduct(User user, Product product);
    List<Favorite> findAllByUser(User user);
}

