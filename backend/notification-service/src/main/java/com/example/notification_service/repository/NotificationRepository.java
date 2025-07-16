package com.example.notification_service.repository;


import com.example.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    List<Notification> findByRecipientId(UUID recipientId);

    List<Notification> findByRecipientIdAndIsRead(UUID recipientId, boolean isRead);
}