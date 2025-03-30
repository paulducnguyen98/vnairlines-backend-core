package com.vnairlines.csdl.models;

import java.sql.Timestamp;
import java.util.UUID;

public class UserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean isAdmin;
    private boolean isLoyaltyMember;
    private Timestamp createdAt;
    private String tierId;
    private String tierName;

    public UserDto() {
        super();
    }

    public UserDto(UUID userId, String firstName, String lastName, String email, String phoneNumber, boolean isAdmin,
            boolean isLoyaltyMember, Timestamp createdAt) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
        this.isLoyaltyMember = isLoyaltyMember;
        this.createdAt = createdAt;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public void setLoyaltyMember(boolean isLoyaltyMember) {
        this.isLoyaltyMember = isLoyaltyMember;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTierId() {
        return tierId;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierId(String tierId) {
        this.tierId = tierId;
    }
}
