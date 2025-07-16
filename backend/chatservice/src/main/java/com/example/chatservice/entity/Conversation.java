package com.example.chatservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conversation {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "conversation_participants", joinColumns = @JoinColumn(name = "conversation_id"))
    @Column(name = "participant_id")
    private Set<String> participantIds;

    private Instant createdAt;
    private Instant lastUpdated;

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
        this.lastUpdated = Instant.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastUpdated = Instant.now();
    }
}
