package com.example.friendshipservice.mapper;

import com.example.friendshipservice.dto.FriendRequestDto;
import com.example.friendshipservice.entity.FriendRequest;
import org.springframework.stereotype.Component;

@Component
public class FriendRequestMapper {

    public FriendRequestDto toDto(FriendRequest entity) {
        FriendRequestDto dto = new FriendRequestDto();
        dto.setId(entity.getId());
        dto.setSenderId(entity.getSenderId());
        dto.setRecipientId(entity.getReceiverId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    public FriendRequest toEntity(FriendRequestDto dto) {
        FriendRequest entity = new FriendRequest();
        entity.setId(dto.getId());
        entity.setSenderId(dto.getSenderId());
        entity.setReceiverId(dto.getRecipientId());
        entity.setStatus(dto.getStatus());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
}
