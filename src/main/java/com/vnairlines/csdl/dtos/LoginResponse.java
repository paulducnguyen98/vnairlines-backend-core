package com.vnairlines.csdl.dtos;
import java.util.UUID;

public class LoginResponse {
    private UUID userId;
    private String email;
    private String accessToken;

    public LoginResponse() {
    }

    public LoginResponse(UUID userId, String email, String accessToken) {
        this.userId = userId;
        this.email = email;
        this.accessToken = accessToken;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}