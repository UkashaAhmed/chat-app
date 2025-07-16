package com.example.friendshipservice.repository;

import com.example.friendshipservice.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, String> {

    List<Friendship> findAllByUser1IdOrUser2Id(String user1Id, String user2Id);

    boolean existsByUser1IdAndUser2Id(String user1Id, String user2Id);
}

