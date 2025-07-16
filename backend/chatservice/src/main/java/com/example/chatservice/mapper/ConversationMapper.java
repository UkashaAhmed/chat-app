package com.example.chatservice.mapper;

import com.example.chatservice.dto.ConversationDto;
import com.example.chatservice.entity.Conversation;
import org.springframework.stereotype.Component;

@Component
public class ConversationMapper {

    public ConversationDto toDto(Conversation entity) {
        return ConversationDto.builder()
                .id(entity.getId())
                .participantIds(entity.getParticipantIds())
                .build();
    }
}
