package com.furniture.store.repository;

import com.furniture.store.entity.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("""
        SELECT n FROM Notification n
        WHERE
            n.targetType = 'ALL'
            OR (n.targetType = 'USER' AND n.targetId = :userId)
            OR (n.targetType = 'ROLE' AND n.targetId IN :roleIds)
        ORDER BY n.createdAt DESC
    """)
    Page<Notification> findAllForUser(@Param("userId") String userId,
                                      @Param("roleIds") Set<String> roleIds,
                                      Pageable pageable);

}
