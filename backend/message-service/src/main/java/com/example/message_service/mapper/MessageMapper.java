package com.example.message_service.mapper;


import com.example.message_service.dto.MessageDto;
import com.example.message_service.entity.Message;
import org.springframework.stereotype.Component;
@Component
public class MessageMapper {

    public MessageDto toDto(Message entity) {
        return MessageDto.builder()
                .id(entity.getId())
                .senderId(entity.getSenderId())
                .chatId(entity.getChatId())
                .content(entity.getContent())
                .timestamp(entity.getTimestamp()) // ✅ Must match DTO field name
                .build();
    }

    public Message toEntity(MessageDto dto) {
        return Message.builder()
                .id(dto.getId())
                .senderId(dto.getSenderId())
                .chatId(dto.getChatId())
                .content(dto.getContent())
                .timestamp(dto.getTimestamp()) // ✅ Must match entity field name
                .build();
    }
}