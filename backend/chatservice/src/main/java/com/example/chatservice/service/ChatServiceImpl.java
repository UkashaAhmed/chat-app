package com.example.chatservice.service.impl;

import com.example.chatservice.client.FriendshipClient;
import com.example.chatservice.client.UserClient;
import com.example.chatservice.dto.*;
import com.example.chatservice.entity.Conversation;
import com.example.chatservice.entity.Message;
import com.example.chatservice.mapper.ConversationMapper;
import com.example.chatservice.mapper.MessageMapper;
import com.example.chatservice.repository.ConversationRepository;
import com.example.chatservice.repository.MessageRepository;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;
    private final ConversationMapper conversationMapper;
    private final MessageMapper messageMapper;
    private final UserClient userClient;
    private final FriendshipClient friendshipClient;

    @Override
    public ConversationDto createConversation(CreateConversationRequest request) {
        // Validate users exist via userClient
        for (String participantId : request.getParticipantIds()) {
            userClient.getUserById(participantId);
        }

        Conversation conversation = Conversation.builder()
                .participantIds(request.getParticipantIds())
                .build();

        return conversationMapper.toDto(conversationRepository.save(conversation));
    }

    @Override
    public List<ConversationDto> getConversationsForUser(String userId) {
        return conversationRepository.findByParticipantIdsContaining(userId).stream()
                .map(conversationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MessageDto sendMessage(String conversationId, SendMessageRequest request) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        if (!conversation.getParticipantIds().contains(request.getSenderId()) ||
                !conversation.getParticipantIds().contains(request.getRecipientId())) {
            throw new RuntimeException("Sender or recipient not in conversation");
        }

        Message message = Message.builder()
                .conversation(conversation)
                .senderId(request.getSenderId())
                .recipientId(request.getRecipientId())
                .content(request.getContent())
                .isRead(false)
                .build();

        return messageMapper.toDto(messageRepository.save(message));
    }

    @Override
    public List<MessageDto> getMessagesInConversation(String conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        return messageRepository.findByConversationOrderByTimestampAsc(conversation).stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }
}
