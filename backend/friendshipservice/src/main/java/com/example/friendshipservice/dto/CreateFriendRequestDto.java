package com.example.friendshipservice.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class CreateFriendRequestDto {
    private UUID recipientId;
}
