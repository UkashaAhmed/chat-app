package com.example.friendshipservice.service;

import com.example.friendshipservice.client.UserClient;
import com.example.friendshipservice.dto.FriendRequestDto;
import com.example.friendshipservice.entity.FriendRequest;
import com.example.friendshipservice.entity.FriendRequestStatus;
import com.example.friendshipservice.mapper.FriendRequestMapper;
import com.example.friendshipservice.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendRequestRepository friendRequestRepository;
    private final FriendRequestMapper mapper;
    private final UserClient userClient;

    public FriendshipServiceImpl(
            FriendRequestRepository friendRequestRepository,
            FriendRequestMapper mapper,
            UserClient userClient
    ) {
        this.friendRequestRepository = friendRequestRepository;
        this.mapper = mapper;
        this.userClient = userClient;
    }

    @Override
    public FriendRequestDto sendFriendRequest(UUID senderId, UUID recipientId) {
        // Ensure both users exist
        try {
            userClient.getUserById(senderId);
            userClient.getUserById(recipientId);
        } catch (Exception ex) {
            throw new RuntimeException("Sender or recipient not found in User Service");
        }

        FriendRequest request = new FriendRequest();
        request.setSenderId(senderId);
        request.setRecipientId(recipientId);
        request.setStatus(FriendRequestStatus.PENDING);
        request.setCreatedAt(Instant.now());

        FriendRequest saved = friendRequestRepository.save(request);
        return mapper.toDto(saved);
    }

    @Override
    public List<FriendRequestDto> getPendingRequests(UUID userId) {
        List<FriendRequest> requests = friendRequestRepository
                .findByRecipientIdAndStatus(userId, FriendRequestStatus.PENDING);
        return requests.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public FriendRequestDto acceptRequest(UUID requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        request.setStatus(FriendRequestStatus.ACCEPTED);
        FriendRequest updated = friendRequestRepository.save(request);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteRequest(UUID requestId) {
        if (!friendRequestRepository.existsById(requestId)) {
            throw new RuntimeException("Friend request not found");
        }
        friendRequestRepository.deleteById(requestId);
    }
}
