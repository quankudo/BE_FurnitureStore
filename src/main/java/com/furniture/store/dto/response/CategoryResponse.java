package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponse {
    private String id;
    private String name;
    private String slug;
    private String imageUrl;
    private Boolean isActive;
    private String desc;
}
