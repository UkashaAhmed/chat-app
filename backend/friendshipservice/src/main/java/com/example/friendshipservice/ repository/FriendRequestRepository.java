package com.example.friendshipservice.repository;

import com.example.friendshipservice.entity.FriendRequest;
import com.example.friendshipservice.entity.FriendRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, UUID> {

    List<FriendRequest> findAllBySenderIdOrRecipientId(UUID senderId, UUID recipientId);

    List<FriendRequest> findByRecipientIdAndStatus(UUID recipientId, FriendRequestStatus status);

}
