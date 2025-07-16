package com.example.chatservice.repository;

import com.example.chatservice.entity.Message;
import com.example.chatservice.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, String> {
    List<Message> findByConversationOrderByTimestampAsc(Conversation conversation);

    List<Message> findByRecipientIdAndIsReadFalse(String recipientId);
}
