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
    ProductInfoRequest productInfoRequest;

    // Các thuộc tính động (ví dụ: Kích thước, Chất liệu bề mặt, Xuất xứ...)
    List<ProductAttributeRequest> attributes;

    // Các biến thể (mỗi biến thể có màu, chất liệu, giá, tồn kho, ảnh riêng)
    List<ProductVariantRequest> variants;
}
