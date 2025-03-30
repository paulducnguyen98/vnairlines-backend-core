package com.vnairlines.csdl.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class PassengerResponse {

    private UUID passengerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;
    private String passportNumber;
    private boolean isMainContact;
    private LocalDateTime createdAt;

    public PassengerResponse() {
        // TODO Auto-generated constructor stub
    }

    public PassengerResponse(UUID passengerId, String firstName, String lastName, String email, String phoneNumber,
            LocalDate birthDate, String passportNumber, boolean isMainContact, LocalDateTime createdAt) {
        super();
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.passportNumber = passportNumber;
        this.isMainContact = isMainContact;
        this.createdAt = createdAt;
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(UUID passengerId) {
        this.passengerId = passengerId;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public boolean isMainContact() {
        return isMainContact;
    }

    public void setMainContact(boolean isMainContact) {
        this.isMainContact = isMainContact;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
