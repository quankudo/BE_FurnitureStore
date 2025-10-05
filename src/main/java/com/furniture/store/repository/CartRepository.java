package com.furniture.store.repository;

import com.furniture.store.entity.Cart;
import com.furniture.store.entity.Product;
import com.furniture.store.entity.ProductVariant;
import com.furniture.store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
    Optional<Cart> findByUserAndProductVariant(User user, ProductVariant productVariant);
    void deleteByUserAndProductVariant(User user, ProductVariant productVariant);
}


