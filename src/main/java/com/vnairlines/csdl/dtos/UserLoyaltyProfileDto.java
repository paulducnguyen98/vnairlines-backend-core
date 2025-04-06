package com.vnairlines.csdl.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserLoyaltyProfileDto {

    private UUID userId;
    private String fullName;
    private String memberCode;
    private String tierName;
    private Integer totalMiles;
    private Integer totalFlights;
    private LocalDate milesExpiryDate;
    private LocalDateTime tierAchievedAt;

    private List<MileTransactionDto> transactions;
    private List<MileRedemptionDto> redemptions;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
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

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public LocalDateTime getTierAchievedAt() {
        return tierAchievedAt;
    }

    public void setTierAchievedAt(LocalDateTime tierAchievedAt) {
        this.tierAchievedAt = tierAchievedAt;
    }

    public List<MileTransactionDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<MileTransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<MileRedemptionDto> getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(List<MileRedemptionDto> redemptions) {
        this.redemptions = redemptions;
    }

}
