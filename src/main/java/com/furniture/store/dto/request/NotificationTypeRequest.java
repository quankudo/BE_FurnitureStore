package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationTypeRequest {
    String code;
    String name;
    String icon;
    String description;
    LocalDateTime createdAt;
}
