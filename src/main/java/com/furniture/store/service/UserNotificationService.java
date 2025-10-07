package com.furniture.store.service;

import com.furniture.store.entity.Notification;
import com.furniture.store.entity.User;
import com.furniture.store.entity.UserNotification;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.repository.NotificationRepository;
import com.furniture.store.repository.UserNotificationRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserNotificationService {
    UserNotificationRepository userNotificationRepository;
    UserContextService userContextService;
    NotificationRepository notificationRepository;

    public void markAsRead(Long notificationId) {
        User user = userContextService.getCurrentUser();

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(()->new AppException(ErrorCode.NOTIFICATION_NOT_FOUND));

        if(userNotificationRepository.existsByUserAndNotification(user, notification)) {
            return;
        }

        UserNotification userNotification = UserNotification.builder()
                .user(user)
                .notification(notification)
                .build();

        userNotificationRepository.save(userNotification);
    }
}
