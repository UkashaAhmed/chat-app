package com.example.chatservice.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private String id;
    private String conversationId;
    private String senderId;
    private String recipientId;
    private String content;
    private Instant timestamp;
    private boolean isRead;
}
