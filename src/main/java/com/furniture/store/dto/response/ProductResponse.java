package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    ProductInfoResponse productInfo;

    CategoryResponse category;
    SupplierResponse supplier;

    List<ProductAttributeResponse> attributes;
    List<ProductVariantResponse> variants;
}
