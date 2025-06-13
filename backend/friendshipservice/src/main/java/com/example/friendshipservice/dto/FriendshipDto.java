package com.example.friendshipservice.dto;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class FriendshipDto {
    private UUID id;
    private UUID user1Id;
    private UUID user2Id;
    private Instant createdAt;
}

