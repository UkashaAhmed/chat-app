package com.example.notification_service.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID senderId;
    private UUID recipientId;
    private String message;
    private boolean isRead;
    private Instant createdAt;
}
