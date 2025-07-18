package com.example.chatservice.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class NativeWebSocketHandler implements WebSocketHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Map of conversationId to list of connected sessions
    private final Map<String, Set<WebSocketSession>> conversationSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("WebSocket connected: {}", session.getId());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Incoming message expected to be JSON with conversationId, senderId, recipientId, content
        Map<String, Object> payload = objectMapper.readValue(message.getPayload().toString(), Map.class);
        String conversationId = payload.get("conversationId").toString();

        // Save session into the conversation group
        conversationSessions.computeIfAbsent(conversationId, k -> ConcurrentHashMap.newKeySet()).add(session);

        // Broadcast message to all connected sessions for that conversation
        String json = objectMapper.writeValueAsString(payload);
        for (WebSocketSession s : conversationSessions.get(conversationId)) {
            if (s.isOpen()) {
                s.sendMessage(new TextMessage(json));
            }
        }

        log.info("Message sent to conversation {}: {}", conversationId, json);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("WebSocket error", exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // Cleanup session from all conversations
        conversationSessions.values().forEach(set -> set.remove(session));
        log.info("WebSocket closed: {}", session.getId());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
