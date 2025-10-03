package com.furniture.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlogResponse {
    Long id;
    String title;
    String slug;
    String summary;
    String content;
    String thumbnail;
    Boolean published;
    BlogCategoryResponse BlogCategory;
    Set<TagResponse> listTags;
    UserResponse author;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
