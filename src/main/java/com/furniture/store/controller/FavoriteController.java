package com.furniture.store.controller;

import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.ProductInfoResponse;
import com.furniture.store.service.FavoriteService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FavoriteController {
    FavoriteService favoriteService;

    @PostMapping("/{productId}")
    public ApiResponse<Void> toggleFavorite(@PathVariable String productId) {
        favoriteService.toggleFavorite(productId);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping
    public ApiResponse<List<ProductInfoResponse>> getUserFavorites() {
        return ApiResponse.<List<ProductInfoResponse>>builder()
                .result(favoriteService.getUserFavorites())
                .build();
    }
}

