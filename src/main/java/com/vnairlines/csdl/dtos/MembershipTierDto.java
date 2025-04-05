package com.vnairlines.csdl.dtos;

import java.util.UUID;

public class MembershipTierDto {

    private UUID tierId;
    private String tierName;
    private int tierRank;
    private int requiredMiles;
    private int requiredFlights;
    private String benefits;

    public UUID getTierId() {
        return tierId;
    }

    public void setTierId(UUID tierId) {
        this.tierId = tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public int getTierRank() {
        return tierRank;
    }

    public void setTierRank(int tierRank) {
        this.tierRank = tierRank;
    }

    public int getRequiredMiles() {
        return requiredMiles;
    }

    public void setRequiredMiles(int requiredMiles) {
        this.requiredMiles = requiredMiles;
    }

    public int getRequiredFlights() {
        return requiredFlights;
    }

    public void setRequiredFlights(int requiredFlights) {
        this.requiredFlights = requiredFlights;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

}
