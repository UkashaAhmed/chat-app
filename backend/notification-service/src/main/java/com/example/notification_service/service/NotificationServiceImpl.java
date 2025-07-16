package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.entity.Notification;
import com.example.notification_service.mapper.NotificationMapper;
import com.example.notification_service.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public NotificationDto sendNotification(NotificationDto notificationDto) {
        notificationDto.setCreatedAt(Instant.now());
        Notification notification = notificationMapper.toEntity(notificationDto);
        Notification saved = notificationRepository.save(notification);
        return notificationMapper.toDto(saved);
    }

    @Override
    public List<NotificationDto> getNotificationsForUser(UUID userId) {
        return notificationRepository.findByRecipientId(userId)
                .stream()
                .map(notificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto markAsRead(UUID notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
        return null;
    }
}