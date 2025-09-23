package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {
    // Thông tin cơ bản sản phẩm
    String productName;
    String description;
    String slug;
    String imageUrl;
    Long categoryId;

    // Các thuộc tính động (ví dụ: Kích thước, Chất liệu bề mặt, Xuất xứ...)
    List<ProductAttributeRequest> attributes;

    // Các biến thể (mỗi biến thể có màu, chất liệu, giá, tồn kho, ảnh riêng)
    List<ProductVariantRequest> variants;

    // ------------------ Inner DTO ------------------

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductAttributeRequest {
        String name;
        String value;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductVariantRequest {
        Long materialId;
        Long colorId;
        BigDecimal price;
        Integer stockQuantity;
        List<String> images; // list url ảnh cho biến thể
    }
}
