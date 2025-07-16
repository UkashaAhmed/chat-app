package com.example.friendshipservice.dto;

import lombok.Data;

@Data
public class FriendResponseDto {
    private String friendId;
    private String username;
    private String displayName;
    private String avatarUrl;
}
