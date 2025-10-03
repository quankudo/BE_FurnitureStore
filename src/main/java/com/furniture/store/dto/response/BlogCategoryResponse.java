package com.furniture.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogCategoryResponse {
    Long id;
    String name;
    String slug;
    String description;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
