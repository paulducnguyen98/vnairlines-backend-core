package com.vnairlines.csdl.dtos;

import java.util.UUID;

public class TierAssignmentRequest {

    private UUID userId;
    private String tierName;
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }
    public String getTierName() {
        return tierName;
    }
    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    
}
