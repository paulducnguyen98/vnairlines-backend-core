package com.vnairlines.csdl.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

public class UserDto {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Timestamp createdAt;
    private String tierId;
    private String tierName;
    private String address;
    private String gender;
    private String identityNumber;
    private Date identityIssuedDate;
    private String identityIssuedPlace;

    public UserDto() {
        super();
    }

    public UserDto(UUID userId, String firstName, String lastName, String email, String phoneNumber,
            Timestamp createdAt, String tierId, String tierName, String address, String gender, String identityNumber,
            Date identityIssuedDate, String identityIssuedPlace) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.tierId = tierId;
        this.tierName = tierName;
        this.address = address;
        this.gender = gender;
        this.identityNumber = identityNumber;
        this.identityIssuedDate = identityIssuedDate;
        this.identityIssuedPlace = identityIssuedPlace;
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

    // Thêm getter/setter tương ứng

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
