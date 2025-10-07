package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationTypeResponse {
    Long id;
    String code;
    String name;
    String icon;
    String description;
    LocalDateTime createdAt;
}
