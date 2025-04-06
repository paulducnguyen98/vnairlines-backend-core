package com.vnairlines.csdl.services;

import java.util.UUID;

import com.vnairlines.csdl.dtos.UserLoyaltyProfileDto;

public interface LoyaltyService {

    UserLoyaltyProfileDto getLoyaltyProfile(UUID userId);
}
