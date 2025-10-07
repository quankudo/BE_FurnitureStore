package com.furniture.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationResponse {
    Long id;
    String title;
    String content;
    String icon;
    String typeName;
    boolean isRead;
    boolean isSystem;
    LocalDateTime createdAt;
    UserResponse sender;
}
