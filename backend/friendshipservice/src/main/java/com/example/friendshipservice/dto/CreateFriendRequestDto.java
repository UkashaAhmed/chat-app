package com.example.friendshipservice.dto;

import lombok.Data;

@Data
public class CreateFriendRequestDto {

    private String senderId;
    private String recipientId;

public String getSenderId() { return senderId; }
public String getRecipientId() { return recipientId; }
}