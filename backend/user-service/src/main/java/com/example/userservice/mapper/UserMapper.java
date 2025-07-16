package com.example.userservice.mapper;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        if (user == null) return null;

        return new UserDto(
                user.getId().toString(),
                user.getUsername(),
                user.getDisplayName(),
                user.getEmail()
        );
    }

    public static User fromDto(UserDto dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId() != null ? String.valueOf(java.util.UUID.fromString(dto.getId())) : null);
        user.setUsername(dto.getUsername());
        user.setDisplayName(dto.getDisplayName());
        user.setEmail(dto.getEmail());
        return user;
    }
}
