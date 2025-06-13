package com.example.friendshipservice.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID senderId;
    private UUID recipientId;

    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    private Instant createdAt;

    // Constructors
    public FriendRequest() {}

    public FriendRequest(UUID id, UUID senderId, UUID recipientId, FriendRequestStatus status, Instant createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.status = status;
        this.createdAt = createdAt;
    }

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

