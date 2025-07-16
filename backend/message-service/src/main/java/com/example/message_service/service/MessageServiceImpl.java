package com.example.message_service.service;



import com.example.message_service.dto.MessageDto;
import com.example.message_service.entity.Message;
import com.example.message_service.mapper.MessageMapper;
import com.example.message_service.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {


    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public MessageDto sendMessage(MessageDto dto) {
        dto.setTimestamp(Instant.now());  // ✅ Use correct field
        Message saved = messageRepository.save(messageMapper.toEntity(dto));
        return messageMapper.toDto(saved);
    }

    @Override
    public List<MessageDto> getMessagesByChatId(UUID chatId) {
        return messageRepository.findByChatId(chatId).stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }
}