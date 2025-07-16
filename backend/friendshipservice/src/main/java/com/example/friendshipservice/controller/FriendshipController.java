package com.example.friendshipservice.controller;

import com.example.friendshipservice.dto.CreateFriendRequestDto;
import com.example.friendshipservice.dto.FriendRequestDto;
import com.example.friendshipservice.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/friends")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    // Send a friend request
    @PostMapping("/request")
    public ResponseEntity<FriendRequestDto> sendFriendRequest(@RequestBody CreateFriendRequestDto request) {
        String senderId = request.getSenderId();
        String receiverId = request.getRecipientId();
        return ResponseEntity.ok(friendshipService.sendFriendRequest(senderId, receiverId));
    }



    // Get all pending friend requests for a user
    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<FriendRequestDto>> getPendingRequests(@PathVariable String userId) {
        return ResponseEntity.ok(friendshipService.getPendingRequests(userId));
    }
    @GetMapping("/friends/{userId}")
    public ResponseEntity<List<String>> getFriends(@PathVariable String userId) {
        List<String> friendIds = friendshipService.getFriendIds(userId);
        return ResponseEntity.ok(friendIds);
    }

    // Accept a friend request
    @PutMapping("/accept/{requestId}")
    public ResponseEntity<FriendRequestDto> acceptRequest(@PathVariable String requestId) {
        return ResponseEntity.ok(friendshipService.acceptRequest(requestId));
    }

    // Delete a friend request (decline or cancel)
    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable String requestId) {
        friendshipService.deleteRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
