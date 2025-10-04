package com.furniture.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {
    Long id;
    String content;
    UserResponse authorName;
    Long blogId;
    Long parentId;
    LocalDateTime createdAt;
    List<CommentResponse> replies;
}
