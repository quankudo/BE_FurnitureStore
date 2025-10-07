package com.furniture.store.mapper;

import com.furniture.store.dto.request.NotificationTypeRequest;
import com.furniture.store.dto.response.NotificationTypeResponse;
import com.furniture.store.entity.NotificationType;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class NotificationTypeMapper {
    public NotificationType toEntity(NotificationTypeRequest request) {
        return NotificationType.builder()
                .code(request.getCode())
                .name(request.getName())
                .icon(request.getIcon())
                .description(request.getDescription())
                .build();
    }

    public NotificationTypeResponse toResponse(NotificationType notificationType) {
        return NotificationTypeResponse.builder()
                .id(notificationType.getId())
                .code(notificationType.getCode())
                .name(notificationType.getName())
                .icon(notificationType.getIcon())
                .description(notificationType.getDescription())
                .createdAt(notificationType.getCreatedAt())
                .build();
    }

    public void update(NotificationType notificationType, NotificationTypeRequest request) {
        if (!Objects.equals(notificationType.getCode(), request.getCode())) {
            notificationType.setCode(request.getCode());
        }

        if (!Objects.equals(notificationType.getName(), request.getName())) {
            notificationType.setName(request.getName());
        }

        if (!Objects.equals(notificationType.getDescription(), request.getDescription())) {
            notificationType.setDescription(request.getDescription());
        }

        if (!Objects.equals(notificationType.getIcon(), request.getIcon())) {
            notificationType.setIcon(request.getIcon());
        }
    }

}
