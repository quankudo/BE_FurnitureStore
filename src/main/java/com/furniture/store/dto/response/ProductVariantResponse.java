package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductVariantResponse {
    String variantId;
    BigDecimal price;
    Integer stockQuantity;
    ColorResponse color;
    MaterialResponse material;
    List<VariantImageResponse> images;
}
