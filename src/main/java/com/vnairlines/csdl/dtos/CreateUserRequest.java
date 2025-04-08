package com.vnairlines.csdl.dtos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class CreateUserRequest {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;
    private String tierName;
    private String address;
    private String gender;
    private String identityNumber;
    private Date identityIssuedDate;
    private String identityIssuedPlace;
    private String password;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public Date getIdentityIssuedDate() {
        return identityIssuedDate;
    }

    public void setIdentityIssuedDate(Date identityIssuedDate) {
        this.identityIssuedDate = identityIssuedDate;
    }

    public String getIdentityIssuedPlace() {
        return identityIssuedPlace;
    }

    public void setIdentityIssuedPlace(String identityIssuedPlace) {
        this.identityIssuedPlace = identityIssuedPlace;
    }
}
