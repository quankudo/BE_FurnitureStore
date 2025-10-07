package com.furniture.store.dto.request;

import com.furniture.store.enums.TargetType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationRequest {
    Long typeId;
    String title;
    String content;
    TargetType targetType; // "USER", "ROLE", "ALL", ...
    String targetId;
}
