package com.example.userservice.dto;



public class AuthResponse {
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // ✅ No-arg constructor (needed for serialization)
    public AuthResponse() {}

    // ✅ Full constructor (what you're using in service)
    public AuthResponse(String token,String userId) {
        this.token = token;this.userId=userId;
    }

    // ✅ Getter and setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

