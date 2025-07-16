package com.example.chatservice.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendRequestDto {
    private String id;
    private String senderId;
    private String recipientId;
    private String status;
    private Instant createdAt;
}
