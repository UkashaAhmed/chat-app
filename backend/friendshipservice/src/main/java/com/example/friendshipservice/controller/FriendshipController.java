package com.example.friendshipservice.controller;

import com.example.friendshipservice.dto.FriendRequestDto;
import com.example.friendshipservice.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    // Send a friend request
    @PostMapping("/request")
    public ResponseEntity<FriendRequestDto> sendFriendRequest(
            @RequestParam UUID senderId,
            @RequestParam UUID recipientId
    ) {
        FriendRequestDto result = friendshipService.sendFriendRequest(senderId, recipientId);
        return ResponseEntity.ok(result);
    }

    // Get all pending friend requests for a user
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<FriendRequestDto>> getPendingRequests(@PathVariable UUID userId) {
        return ResponseEntity.ok(friendshipService.getPendingRequests(userId));
    }

    // Accept a friend request
    @PutMapping("/accept/{requestId}")
    public ResponseEntity<FriendRequestDto> acceptRequest(@PathVariable UUID requestId) {
        return ResponseEntity.ok(friendshipService.acceptRequest(requestId));
    }

    // Delete a friend request (decline or cancel)
    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable UUID requestId) {
        friendshipService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
