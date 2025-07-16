package com.example.chatservice.repository;

import com.example.chatservice.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ConversationRepository extends JpaRepository<Conversation, String> {
    List<Conversation> findByParticipantIdsContaining(String userId);

    // Optional: To find conversation between specific two users (1-on-1)
    List<Conversation> findByParticipantIdsIn(Set<String> participantIds);
}
