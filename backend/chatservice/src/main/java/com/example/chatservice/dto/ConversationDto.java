package com.example.chatservice.dto;

import lombok.*;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConversationDto {
    private String id;
    private Set<String> participantIds;
    private Instant createdAt;
    private Instant lastUpdated;
}
