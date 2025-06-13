package com.example.friendshipservice.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class FriendResponseDto {
    private UUID friendId;
    private String username;
    private String displayName;
    private String avatarUrl;
}
