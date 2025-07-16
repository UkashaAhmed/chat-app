package com.example.friendshipservice.service;

import com.example.friendshipservice.client.UserClient;
import com.example.friendshipservice.dto.FriendRequestDto;
import com.example.friendshipservice.entity.FriendRequest;
import com.example.friendshipservice.entity.FriendRequestStatus;
import com.example.friendshipservice.entity.Friendship;
import com.example.friendshipservice.mapper.FriendRequestMapper;
import com.example.friendshipservice.repository.FriendRequestRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class    FriendshipServiceImpl implements FriendshipService {

    private final FriendRequestRepository friendRequestRepository;
    private final com.example.friendshipservice.repository.FriendshipRepository friendshipRepository;
    private final FriendRequestMapper mapper;
    private final UserClient userClient;

    public FriendshipServiceImpl(
            FriendRequestRepository friendRequestRepository,
            FriendRequestMapper mapper,
            UserClient userClient, com.example.friendshipservice.repository.FriendshipRepository friendshipRepository
    ) {
        this.friendRequestRepository = friendRequestRepository;
        this.mapper = mapper;
        this.userClient = userClient;
        this. friendshipRepository = friendshipRepository;
    }

    @Override
    public FriendRequestDto sendFriendRequest(String senderId, String recipientId) {
        // Ensure both users exist
        try {
            userClient.getUserById(senderId);
            userClient.getUserById(recipientId);
        } catch (Exception ex) {
            throw new RuntimeException("Sender or recipient not found in User Service");
        }

        FriendRequest request = new FriendRequest();
        request.setSenderId(senderId);
        request.setReceiverId(recipientId);
        request.setStatus(FriendRequestStatus.PENDING);
        request.setCreatedAt(Instant.now());

        FriendRequest saved = friendRequestRepository.save(request);
        return mapper.toDto(saved);
    }
    @Override
    public List<String> getFriendIds(String userId) {

        List<Friendship> friendships = friendshipRepository.findAllByUser1IdOrUser2Id(userId, userId);


        return friendships.stream()
                .map(f -> f.getUser1Id().equals(userId) ? f.getUser2Id() : f.getUser1Id())
                .distinct()
                .toList();
    }
    @Override
    public List<FriendRequestDto> getPendingRequests(String userId) {
        List<FriendRequest> requests = friendRequestRepository
                .findByReceiverIdAndStatus(userId, FriendRequestStatus.PENDING);
        return requests.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public FriendRequestDto acceptRequest(String requestId) {
        FriendRequest request = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found"));

        request.setStatus(FriendRequestStatus.ACCEPTED);
        FriendRequest updated = friendRequestRepository.save(request);
        return mapper.toDto(updated);
    }

    @Override
    public void deleteRequest(String requestId) {
        if (!friendRequestRepository.existsById(requestId)) {
            throw new RuntimeException("Friend request not found");
        }
        friendRequestRepository.deleteById(requestId);
    }
}
