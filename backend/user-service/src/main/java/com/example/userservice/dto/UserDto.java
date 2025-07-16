package com.example.userservice.dto;

import com.example.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String username;
    private String displayName;
    private String email;

    public UserDto(User user) {
        this.id=user.getId();
        this.username=user.getUsername();
        this.displayName=user.getDisplayName();
        this.email= user.getEmail();
    }
}
