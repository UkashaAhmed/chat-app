package com.example.friendshipservice.repository;

import com.example.friendshipservice.entity.FriendRequest;
import com.example.friendshipservice.entity.FriendRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, String> {
    List<FriendRequest> findByReceiverIdAndStatus(String receiverId, FriendRequestStatus status);

    List<FriendRequest> findBySenderIdOrReceiverIdAndStatus(String senderId, String receiverId, FriendRequestStatus status);

}
