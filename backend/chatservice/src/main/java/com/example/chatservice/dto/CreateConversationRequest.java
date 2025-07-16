package com.example.chatservice.dto;

import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateConversationRequest {
    private Set<String> participantIds;
}
