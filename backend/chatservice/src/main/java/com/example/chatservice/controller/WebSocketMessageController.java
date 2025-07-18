/*
package com.example.chatservice.controller;

import com.example.chatservice.dto.MessageDto;
import com.example.chatservice.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketMessageController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/send")
    public void handleMessage(@Payload MessageDto request) {
        // Save the message
        MessageDto saved = chatService.sendMessage(request.getConversationId(), request);

        // Broadcast to topic
        messagingTemplate.convertAndSend(
                "/topic/messages/" + request.getConversationId(), saved
        );
    }
}
*/
