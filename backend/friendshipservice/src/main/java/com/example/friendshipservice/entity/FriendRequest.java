package com.example.friendshipservice.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;

@Entity
public class FriendRequest {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    private String senderId;
    private String receiverId;

    @Enumerated(EnumType.STRING)
    private FriendRequestStatus status;

    private Instant createdAt;

    // Constructors
    public FriendRequest() {}

    public FriendRequest(String id, String senderId, String recipientId, FriendRequestStatus status, Instant createdAt) {
        this.id = id;
        this.senderId = senderId;
        this.receiverId = recipientId;
        this.status = status;
        this.createdAt = createdAt;
    }

    // Getters
    public String getId() { return id; }
    public String getSenderId() { return senderId; }
    public String getReceiverId() { return receiverId; }
    public FriendRequestStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setSenderId(String senderId) { this.senderId = senderId; }
    public void setReceiverId(String receiverId) { this.receiverId = receiverId; }
    public void setStatus(FriendRequestStatus status) { this.status = status; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

