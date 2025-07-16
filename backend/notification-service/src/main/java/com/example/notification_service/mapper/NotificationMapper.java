package com.example.notification_service.mapper;


import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public NotificationDto toDto(Notification entity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(entity.getId());
        dto.setSenderId(entity.getSenderId());
        dto.setRecipientId(entity.getRecipientId());
        dto.setMessage(entity.getMessage());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRead(entity.isRead());
        return dto;
    }

    public Notification toEntity(NotificationDto dto) {
        Notification entity = new Notification();
        entity.setId(dto.getId());
        entity.setSenderId(dto.getSenderId());
        entity.setRecipientId(dto.getRecipientId());
        entity.setMessage(dto.getMessage());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setRead(dto.isRead());
        return entity;
    }
}