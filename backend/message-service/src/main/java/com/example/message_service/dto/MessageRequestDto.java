package com.example.message_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MessageRequestDto {
    private UUID chatId;
    private UUID senderId;
    private String content;
}
