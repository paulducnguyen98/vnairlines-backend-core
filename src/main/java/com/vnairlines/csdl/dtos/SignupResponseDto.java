package com.vnairlines.csdl.dtos;

import com.vnairlines.csdl.models.UserDto;

public class SignupResponseDto {
    private UserDto user;
    private String accessToken;

    public SignupResponseDto() {}

    public SignupResponseDto(UserDto user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
