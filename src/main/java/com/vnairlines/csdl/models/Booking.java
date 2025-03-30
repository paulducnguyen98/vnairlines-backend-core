package com.vnairlines.csdl.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Booking {

    private UUID bookingId;
    private UUID userId;
    private String bookingCode;
    private String contactFirstName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhone;
    private BigDecimal totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private UUID tripReferenceId;

    public Booking() {
        // TODO Auto-generated constructor stub
    }

    public Booking(UUID bookingId, UUID userId, String bookingCode, String contactFirstName, String contactLastName,
            String contactEmail, String contactPhone, BigDecimal totalPrice, String status, LocalDateTime createdAt,
            UUID tripReferenceId) {
        super();
        this.bookingId = bookingId;
        this.userId = userId;
        this.bookingCode = bookingCode;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
        this.tripReferenceId = tripReferenceId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getTripReferenceId() {
        return tripReferenceId;
    }

    public void setTripReferenceId(UUID tripReferenceId) {
        this.tripReferenceId = tripReferenceId;
    }
}
