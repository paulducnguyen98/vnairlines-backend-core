package com.vnairlines.csdl.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.MembershipTierDto;
import com.vnairlines.csdl.dtos.TierAssignmentRequest;
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
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
        UserDto createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/assign-tier")
    public ResponseEntity<?> assignTierToUser(@RequestBody TierAssignmentRequest request) {
        userService.assignTierToUser(request.getUserId(), request.getTierName());
        return ResponseEntity.ok("Membership tier assigned successfully.");
    }

    @GetMapping("/tiers")
    public ResponseEntity<List<MembershipTierDto>> listAllTiers() {
        List<MembershipTierDto> tiers = userService.getAllMembershipTiers();
        return ResponseEntity.ok(tiers);
    }

}
