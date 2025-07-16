package com.example.friendshipservice.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class FriendshipDto {
    private String id;
    private String user1Id;
    private String user2Id;
    private Instant createdAt;
}

