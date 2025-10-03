package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogRequest {
    String title;
    String summary;
    String content;
    String thumbnail;
    Long idBlogCategory;
    Set<Long> listTags;
}
