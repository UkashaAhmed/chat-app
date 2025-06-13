package com.example.friendshipservice.repository;

import com.example.friendshipservice.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FriendshipRepository extends JpaRepository<Friendship, UUID> {

    List<Friendship> findAllByUser1IdOrUser2Id(UUID user1Id, UUID user2Id);

    boolean existsByUser1IdAndUser2Id(UUID user1Id, UUID user2Id);
}
