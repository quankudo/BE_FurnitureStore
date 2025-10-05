package com.furniture.store.controller;

import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.CartResponse;
import com.furniture.store.service.CartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;

    @PostMapping("/{productId}")
    public ApiResponse<Void> addToCart(
            @PathVariable String productId,
            @RequestParam(defaultValue = "1") int quantity) {
        cartService.addToCart(productId, quantity);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/{productId}")
    public ApiResponse<Void> updateQuantity(
            @PathVariable String productId,
            @RequestParam int quantity) {
        cartService.updateQuantity(productId, quantity);
        return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<Void> removeFromCart(@PathVariable String productId) {
        cartService.removeFromCart(productId);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping
    public ApiResponse<List<CartResponse>> getUserCart() {
        return ApiResponse.<List<CartResponse>>builder()
                .result(cartService.getUserCart())
                .build();
    }
}