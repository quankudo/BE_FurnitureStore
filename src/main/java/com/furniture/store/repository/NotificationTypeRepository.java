package com.furniture.store.repository;

import com.furniture.store.entity.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    boolean existsByName(String name);
}
