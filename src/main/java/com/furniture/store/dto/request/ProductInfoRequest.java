package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductInfoRequest {
    String productName;
    String description;
    String slug;
    String imageUrl;
    String imagePublicId;
    String categoryId;
    Long supplierId;
}
