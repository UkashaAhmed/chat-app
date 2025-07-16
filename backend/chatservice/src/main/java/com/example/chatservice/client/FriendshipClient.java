package com.example.chatservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "friendship-service", url = "http://localhost:8082/api/friends")
public interface FriendshipClient {

    @GetMapping("/friends/{userId}")
    List<UUID> getFriends(@PathVariable("userId") String userId);
}
