package com.example.friendshipservice.service;

import com.example.friendshipservice.dto.FriendRequestDto;

import java.util.List;

public interface FriendshipService {
    FriendRequestDto sendFriendRequest(String senderId, String recipientId);
    List<FriendRequestDto> getPendingRequests(String userId);
    FriendRequestDto acceptRequest(String requestId);
    void deleteRequest(String requestId);
    List<String> getFriendIds(String userId);

}
