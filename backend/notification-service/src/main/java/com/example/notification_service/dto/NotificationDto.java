package com.example.notification_service.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {

    private UUID id;
    private UUID senderId;
    private UUID recipientId;
    private String message;
    private boolean read;
    private Instant createdAt;
}