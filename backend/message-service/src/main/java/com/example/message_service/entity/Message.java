package com.example.message_service.entity;


import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID senderId;

    @Column(nullable = false)
    private UUID chatId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant timestamp;
}