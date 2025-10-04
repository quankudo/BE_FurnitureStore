package com.furniture.store.dto.request;

import com.furniture.store.enums.ReactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReactionRequest {
    Long idBlog;
    ReactionType type;
}
