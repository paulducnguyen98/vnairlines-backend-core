package com.vnairlines.csdl.dtos;

import java.time.LocalDate;
import java.util.UUID;

public class LoyaltyInfoDto {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String tierName;
    private Integer tierRank;
    private Integer totalMiles;
    private Integer totalFlights;
    private LocalDate milesExpiryDate;
    private Integer requiredMiles;
    private Integer requiredFlights;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public Integer getTierRank() {
        return tierRank;
    }

    public void setTierRank(Integer tierRank) {
        this.tierRank = tierRank;
    }

    public Integer getTotalMiles() {
        return totalMiles;
    }

    public void setTotalMiles(Integer totalMiles) {
        this.totalMiles = totalMiles;
    }

    public Integer getTotalFlights() {
        return totalFlights;
    }

    public void setTotalFlights(Integer totalFlights) {
        this.totalFlights = totalFlights;
    }

    public LocalDate getMilesExpiryDate() {
        return milesExpiryDate;
    }

    public void setMilesExpiryDate(LocalDate milesExpiryDate) {
        this.milesExpiryDate = milesExpiryDate;
    }

    public Integer getRequiredMiles() {
        return requiredMiles;
    }

    public void setRequiredMiles(Integer requiredMiles) {
        this.requiredMiles = requiredMiles;
    }

    public Integer getRequiredFlights() {
        return requiredFlights;
    }

    public void setRequiredFlights(Integer requiredFlights) {
        this.requiredFlights = requiredFlights;
    }

}