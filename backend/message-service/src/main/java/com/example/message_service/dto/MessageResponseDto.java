package com.example.message_service.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class MessageResponseDto {
    private UUID id;
    private UUID chatId;
    private UUID senderId;
    private String content;
    private Instant sentAt;
}