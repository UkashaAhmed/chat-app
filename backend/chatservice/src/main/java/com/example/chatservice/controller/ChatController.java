package com.example.chatservice.controller;

import com.example.chatservice.dto.ConversationDto;
import com.example.chatservice.dto.CreateConversationRequest;
import com.example.chatservice.dto.MessageDto;
import com.example.chatservice.dto.SendMessageRequest;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/chats")
public class ChatController {

    private final ChatService chatService;
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }
    @PostMapping("/conversations")
    public ResponseEntity<ConversationDto> createConversation(
            @RequestBody CreateConversationRequest request) {
        return ResponseEntity.ok(chatService.createConversation(request));
    }

    @GetMapping("/conversations/{userId}")
    public ResponseEntity<List<ConversationDto>> getConversationsForUser(
            @PathVariable String userId) {
        return ResponseEntity.ok(chatService.getConversationsForUser(userId));
    }

    @PostMapping("/messages/{conversationId}")
    public ResponseEntity<MessageDto> sendMessage(
            @PathVariable String conversationId,
            @RequestBody SendMessageRequest request) {
        return ResponseEntity.ok(chatService.sendMessage(conversationId, request));
    }

    @GetMapping("/messages/{conversationId}")
    public ResponseEntity<List<MessageDto>> getMessagesInConversation(
            @PathVariable String conversationId) {
        return ResponseEntity.ok(chatService.getMessagesInConversation(conversationId));
    }
}
