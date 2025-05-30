package com.example.userservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;


    public String getUsername() {
        return username;
    }

    public CharSequence getPassword() {
        return password;
    }
}
