package com.vnairlines.csdl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.LoginRequest;
import com.vnairlines.csdl.dtos.LoginResponse;
import com.vnairlines.csdl.dtos.SignUpRequest;
import com.vnairlines.csdl.dtos.SignupResponseDto;
import com.vnairlines.csdl.models.UserDto;
import com.vnairlines.csdl.services.UserService;
import com.vnairlines.csdl.util.JwtUtil;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/auth/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpRequest request) {
        UserDto createdUser = userService.signUp(request);
        return ResponseEntity.ok(createdUser);
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse user = userService.login(request);
        return ResponseEntity.ok(user);
    }
}
