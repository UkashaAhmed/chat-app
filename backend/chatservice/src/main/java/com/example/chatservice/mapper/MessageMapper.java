package com.example.chatservice.mapper;

import com.example.chatservice.dto.MessageDto;
import com.example.chatservice.entity.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .recipientId(message.getRecipientId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .isRead(message.isRead())
                .build();
    }
}
