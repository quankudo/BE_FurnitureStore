package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponse {
    ProductInfoResponse productInfo;
    ProductVariantResponse variantInfo;
    int quantity;
    LocalDateTime createdAt;
}
