package com.example.userservice.dto;



public class AuthResponse {
    private String token;

    // ✅ No-arg constructor (needed for serialization)
    public AuthResponse() {}

    // ✅ Full constructor (what you're using in service)
    public AuthResponse(String token) {
        this.token = token;
    }

    // ✅ Getter and setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

