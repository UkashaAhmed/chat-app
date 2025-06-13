package com.example.friendshipservice.service;

import com.example.friendshipservice.dto.FriendRequestDto;

import java.util.List;
import java.util.UUID;

public interface FriendshipService {
    FriendRequestDto sendFriendRequest(UUID senderId, UUID recipientId);
    List<FriendRequestDto> getPendingRequests(UUID userId);
    FriendRequestDto acceptRequest(UUID requestId);
    void deleteRequest(UUID requestId);
}
