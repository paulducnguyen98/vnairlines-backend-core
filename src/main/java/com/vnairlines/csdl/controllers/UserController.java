package com.vnairlines.csdl.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.CreateUserRequest;
import com.vnairlines.csdl.dtos.MembershipTierDto;
import com.vnairlines.csdl.models.UserDto;
import com.vnairlines.csdl.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID userId) {
        UserDto user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest user) {
        UserDto createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable UUID userId,
            @RequestBody UserDto userDto) {
        userDto.setUserId(userId);
        UserDto updatedUser = userService.updateUser(userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/check-phone")
    public ResponseEntity<Boolean> checkPhoneNumberUsed(
            @RequestParam String phoneNumber,
            @RequestParam(required = false) UUID excludeUserId // optional
    ) {
        boolean exists = userService.isPhoneNumberTaken(phoneNumber, excludeUserId);
        return ResponseEntity.ok(exists);
    }


    @GetMapping("/tiers")
    public ResponseEntity<List<MembershipTierDto>> listAllTiers() {
        List<MembershipTierDto> tiers = userService.getAllMembershipTiers();
        return ResponseEntity.ok(tiers);
    }

}
