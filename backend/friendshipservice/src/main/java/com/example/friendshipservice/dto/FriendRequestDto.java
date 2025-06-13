package com.example.friendshipservice.dto;

import com.example.friendshipservice.entity.FriendRequestStatus;

import java.time.Instant;
import java.util.UUID;

public class FriendRequestDto {

    private UUID id;
    private UUID senderId;
    private UUID recipientId;
    private FriendRequestStatus status;
    private Instant createdAt;

    // Getters
    public UUID getId() { return id; }
    public UUID getSenderId() { return senderId; }
    public UUID getRecipientId() { return recipientId; }
    public FriendRequestStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    // Setters
    public void setId(UUID id) { this.id = id; }
    public void setSenderId(UUID senderId) { this.senderId = senderId; }
    public void setRecipientId(UUID recipientId) { this.recipientId = recipientId; }
    public void setStatus(FriendRequestStatus status) { this.status = status; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}