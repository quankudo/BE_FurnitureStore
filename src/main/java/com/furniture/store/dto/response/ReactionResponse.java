package com.furniture.store.dto.response;

import com.furniture.store.enums.ReactionType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReactionResponse {
    Long id;
    Long idBlog;
    UserResponse user;
    ReactionType type;
    LocalDateTime createdAt;
}
