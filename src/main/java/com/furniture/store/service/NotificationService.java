package com.furniture.store.service;

import com.furniture.store.dto.request.NotificationRequest;
import com.furniture.store.dto.response.NotificationResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.entity.Notification;
import com.furniture.store.entity.NotificationType;
import com.furniture.store.entity.Role;
import com.furniture.store.entity.User;
import com.furniture.store.enums.TargetType;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.NotificationMapper;
import com.furniture.store.repository.NotificationRepository;
import com.furniture.store.repository.NotificationTypeRepository;
import com.furniture.store.repository.UserNotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService {
    NotificationRepository notificationRepository;
    NotificationMapper notificationMapper;
    UserContextService userContextService;
    UserNotificationRepository userNotificationRepository;
    NotificationTypeRepository notificationTypeRepository;

    @Transactional
    public void create(NotificationRequest request) {
        Notification notification = notificationMapper.toEntity(request);

        TargetType targetTypeRequest = request.getTargetType();
        if(TargetType.USER.equals(targetTypeRequest) || TargetType.ROLE.equals(targetTypeRequest)) {
            notification.setTargetId(request.getTargetId());
        }

        User sender = userContextService.getCurrentUser();
        if(sender!=null) {
            notification.setSender(sender);
            notification.setIsSystem(false);
        }
        else {
            notification.setIsSystem(true);
        }

        NotificationType notificationType = notificationTypeRepository.findById(request.getTypeId())
                .orElseThrow(()->new AppException(ErrorCode.NOTIFICATION_TYPE_NOT_FOUND));
        notification.setType(notificationType);

        notificationRepository.save(notification);
    }

    public PaginationResponse<NotificationResponse> getByUser(int page, int size) {
        User user = userContextService.getCurrentUser();
        Set<String> roleIds = user.getRoles()
                .stream()
                .map(Role::getId)
                .collect(Collectors.toSet());

        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Notification> notifications = notificationRepository.findAllForUser(user.getId(), roleIds, pageable);

        // Lấy List id thông báo đã đọc để no query từng dòng
        List<Long> readIds = userNotificationRepository.findAllReadNotificationIds(user.getId());
        Set<Long> readSet = new HashSet<>(readIds);

        return new PaginationResponse<>(
                notifications.stream().map(notification -> {
                    boolean isRead = readSet.contains(notification.getId());
                    return notificationMapper.toResponse(notification, isRead);
                }).toList(),
                notifications.getNumber(),
                notifications.getTotalPages(),
                notifications.getTotalElements()
        );
    }
}
