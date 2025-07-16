package com.example.chatservice.service;

import com.example.chatservice.dto.ConversationDto;
import com.example.chatservice.dto.CreateConversationRequest;
import com.example.chatservice.dto.MessageDto;
import com.example.chatservice.dto.SendMessageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ChatService {
    ConversationDto createConversation(CreateConversationRequest request);
    List<ConversationDto> getConversationsForUser(String userId);
    MessageDto sendMessage(String conversationId, SendMessageRequest request);
    List<MessageDto> getMessagesInConversation(String conversationId);
}
