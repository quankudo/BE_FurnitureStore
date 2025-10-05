package com.furniture.store.service;

import com.furniture.store.dto.response.ProductInfoResponse;
import com.furniture.store.entity.Favorite;
import com.furniture.store.entity.Product;
import com.furniture.store.entity.User;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.ProductMapper;
import com.furniture.store.repository.FavoriteRepository;
import com.furniture.store.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteService {

    FavoriteRepository favoriteRepository;
    ProductRepository productRepository;
    UserContextService userContextService;
    ProductMapper productMapper;

    @Transactional
    public void toggleFavorite(String productId) {
        User user = userContextService.getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (favoriteRepository.existsByUserAndProduct(user, product)) {
            favoriteRepository.deleteByUserAndProduct(user, product);
        } else {
            favoriteRepository.save(Favorite.builder()
                    .user(user)
                    .product(product)
                    .build());
        }
    }

    public List<ProductInfoResponse> getUserFavorites() {
        User user = userContextService.getCurrentUser();
        return favoriteRepository.findAllByUser(user)
                .stream()
                .map(f -> productMapper.toProductInfoResponse(f.getProduct()))
                .toList();
    }
}
