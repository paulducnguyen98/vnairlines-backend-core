package com.vnairlines.csdl.controllers;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.UserLoyaltyProfileDto;
import com.vnairlines.csdl.services.LoyaltyService;

@RestController
@RequestMapping("/api/loyalty")
public class LoyaltyController {

    private final LoyaltyService loyaltyService;

    public LoyaltyController(LoyaltyService loyaltyService) {
        this.loyaltyService = loyaltyService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserLoyaltyProfileDto> getUserLoyaltyProfile(@PathVariable UUID userId) {
        return ResponseEntity.ok(loyaltyService.getLoyaltyProfile(userId));
    }
}
