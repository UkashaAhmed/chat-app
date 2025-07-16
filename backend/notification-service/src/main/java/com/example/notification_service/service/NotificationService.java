package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    NotificationDto sendNotification(NotificationDto notificationDto);
    List<NotificationDto> getNotificationsForUser(UUID userId);
    NotificationDto markAsRead(UUID notificationId);
}