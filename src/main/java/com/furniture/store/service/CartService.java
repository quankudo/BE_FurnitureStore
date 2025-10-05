package com.furniture.store.service;

import com.furniture.store.dto.response.CartResponse;
import com.furniture.store.entity.Cart;
import com.furniture.store.entity.ProductVariant;
import com.furniture.store.entity.User;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.ProductMapper;
import com.furniture.store.repository.CartRepository;
import com.furniture.store.repository.ProductVariantRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartRepository cartRepository;
    ProductVariantRepository productVariantRepository;
    ProductMapper productMapper;
    UserContextService userContextService;

    /**
     * ✅ Thêm biến thể sản phẩm vào giỏ hàng
     * - Nếu đã có thì tăng số lượng
     * - Nếu chưa có thì thêm mới
     */
    @Transactional
    public void addToCart(String variantId, int quantity) {
        if (quantity <= 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        User user = userContextService.getCurrentUser();
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Cart cart = cartRepository.findByUserAndProductVariant(user, variant)
                .orElseGet(() -> Cart.builder()
                        .user(user)
                        .productVariant(variant)
                        .quantity(0)
                        .build());

        cart.setQuantity(cart.getQuantity() + quantity);
        cartRepository.save(cart);
    }

    /**
     * ✅ Cập nhật số lượng biến thể trong giỏ hàng
     * - Nếu quantity = 0 → xóa khỏi giỏ
     */
    @Transactional
    public void updateQuantity(String variantId, int quantity) {
        if (quantity < 0) {
            throw new AppException(ErrorCode.INVALID_QUANTITY);
        }

        User user = userContextService.getCurrentUser();
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Cart cart = cartRepository.findByUserAndProductVariant(user, variant)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        if (quantity == 0) {
            cartRepository.delete(cart);
        } else {
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    /**
     * ✅ Xóa biến thể khỏi giỏ hàng
     */
    @Transactional
    public void removeFromCart(String variantId) {
        User user = userContextService.getCurrentUser();
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        cartRepository.deleteByUserAndProductVariant(user, variant);
    }

    /**
     * ✅ Lấy danh sách giỏ hàng của người dùng hiện tại
     */
    @Transactional(readOnly = true)
    public List<CartResponse> getUserCart() {
        User user = userContextService.getCurrentUser();
        return cartRepository.findAllByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    /**
     * ✅ Chuyển entity -> response
     */
    private CartResponse toResponse(Cart cart) {
        return CartResponse.builder()
                .createdAt(cart.getCreatedAt())
                .quantity(cart.getQuantity())
                .productInfo(productMapper.toProductInfoResponse(
                        cart.getProductVariant().getProduct()))
                .variantInfo(productMapper.toProductVariantResponse(cart.getProductVariant()))
                .build();
    }
}