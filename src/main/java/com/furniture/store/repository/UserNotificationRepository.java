package com.furniture.store.repository;

import com.furniture.store.entity.Notification;
import com.furniture.store.entity.User;
import com.furniture.store.entity.UserNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {
    boolean existsByUserAndNotification(User user, Notification notification);
    @Query("SELECT un.notification.id FROM UserNotification un WHERE un.user.id = :userId")
    List<Long> findAllReadNotificationIds(@Param("userId") String userId);

}
