package com.example.friendshipservice.client;

import com.example.friendshipservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081") // adjust URL if needed
public interface UserClient {

    @GetMapping("/api/auth/users/{id}")
    UserDto getUserById(@PathVariable String id);
}
