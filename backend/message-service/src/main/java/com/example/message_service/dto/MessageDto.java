package com.example.message_service.dto;

import lombok.*;


import java.time.Instant;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    private UUID id;
    private UUID senderId;
    private UUID chatId;
    private String content;
    private Instant timestamp; // ✅ Make sure this matches your entity's field
}