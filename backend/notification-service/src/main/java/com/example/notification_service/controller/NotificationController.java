package com.example.notification_service.controller;


import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    // REST endpoint to send a notification
    @PostMapping
    public NotificationDto sendNotification(@RequestBody NotificationDto dto) {
        NotificationDto saved = notificationService.sendNotification(dto);
        // Send it over WebSocket to recipient
        messagingTemplate.convertAndSend("/topic/notifications/" + saved.getRecipientId(), saved);
        return saved;
    }

    // REST endpoint to get all notifications for a user
    @GetMapping("/user/{userId}")
    public List<NotificationDto> getUserNotifications(@PathVariable UUID userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    // REST endpoint to mark a notification as read
    @PatchMapping("/{id}/read")
    public NotificationDto markAsRead(@PathVariable UUID id) {
        return notificationService.markAsRead(id);
    }

    // Optional: WebSocket endpoint (if you want to allow clients to send messages via /app/send-notification)
    @MessageMapping("/send-notification")
    public void sendViaWebSocket(NotificationDto dto) {
        NotificationDto saved = notificationService.sendNotification(dto);
        messagingTemplate.convertAndSend("/topic/notifications/" + saved.getRecipientId(), saved);
    }
}