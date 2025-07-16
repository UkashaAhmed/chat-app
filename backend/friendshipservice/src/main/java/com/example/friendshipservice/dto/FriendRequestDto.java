package com.example.friendshipservice.dto;

import com.example.friendshipservice.entity.FriendRequestStatus;

import java.time.Instant;

public class FriendRequestDto {

    private String id;
    private String senderId;
    private String recipientId;
    private FriendRequestStatus status;
    private Instant createdAt;

    // Getters
    public String getId() { return id; }
    public String getSenderId() { return senderId; }
    public String getRecipientId() { return recipientId; }
    public FriendRequestStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setRecipientId(String recipientId) { this.recipientId = recipientId; }
    public void setStatus(FriendRequestStatus status) { this.status = status; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}