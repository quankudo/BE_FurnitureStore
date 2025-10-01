package com.furniture.store.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVariantRequest {
    Long materialId;
    Long colorId;
    BigDecimal price;
    Integer stockQuantity;
    List<ImageRequest> images;
}
