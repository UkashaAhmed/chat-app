package com.example.message_service.service;

import com.example.message_service.dto.MessageDto;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    MessageDto sendMessage(MessageDto messageDto);
    List<MessageDto> getMessagesByChatId(UUID chatId);
}