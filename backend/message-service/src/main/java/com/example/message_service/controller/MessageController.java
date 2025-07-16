package com.example.message_service.controller;


import com.example.message_service.dto.MessageDto;
import com.example.message_service.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@RequestBody MessageDto messageDto) {
        MessageDto savedMessage = messageService.sendMessage(messageDto);
        return ResponseEntity.ok(savedMessage);
    }

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDto>> getMessagesByChatId(@PathVariable UUID chatId) {
        List<MessageDto> messages = messageService.getMessagesByChatId(chatId);
        return ResponseEntity.ok(messages);
    }
}