package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductInfoResponse {
    String id;
    String productName;
    String description;
    String slug;
    String imagePublicId;
    String imageUrl;
}
