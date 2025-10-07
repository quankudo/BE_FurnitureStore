package com.furniture.store.mapper;

import com.furniture.store.dto.request.NotificationRequest;
import com.furniture.store.dto.response.NotificationResponse;
import com.furniture.store.entity.Notification;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationMapper {
    UserMapper userMapper;

    public NotificationResponse toResponse(Notification notification, boolean isRead) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .title(notification.getTitle())
                .content(notification.getContent())
                .icon(notification.getType().getIcon())
                .typeName(notification.getType().getName())
                .isRead(isRead)
                .isSystem(notification.getIsSystem())
                .createdAt(notification.getCreatedAt())
                .sender(notification.getSender() != null ? userMapper.toResponse(notification.getSender()) : null)
                .build();
    }

    public Notification toEntity(NotificationRequest request) {
        return Notification.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .targetType(request.getTargetType())
                .build();
    }
}
