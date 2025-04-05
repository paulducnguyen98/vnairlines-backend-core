package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingRequest {
    private UUID userId;
    private List<UUID> flightIds;
    private String contactFirstName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhone;
    private BigDecimal totalPrice;
    //private Map<UUID, String> ticketClasses;
    private List<FlightBookingDetail> flightDetails;

    public BookingRequest() {
        // TODO Auto-generated constructor stub
    }

    public BookingRequest(UUID userId, List<UUID> flightIds, String contactFirstName, String contactLastName,
            String contactEmail, String contactPhone, BigDecimal totalPrice, List<PassengerRequest> passengers,
            List<FlightBookingDetail> flightDetails) {
        super();
        this.userId = userId;
        this.flightIds = flightIds;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.totalPrice = totalPrice;
        this.passengers = passengers;
        this.flightDetails = flightDetails;
    }

    private List<PassengerRequest> passengers;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<UUID> getFlightIds() {
        return flightIds;
    }

    public void setFlightIds(List<UUID> flightIds) {
        this.flightIds = flightIds;
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

    public List<PassengerRequest> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerRequest> passengers) {
        this.passengers = passengers;
    }

    public List<FlightBookingDetail> getFlightDetails() {
        return flightDetails;
    }

    public void setFlightDetails(List<FlightBookingDetail> details) {
        this.flightDetails = details;
    }
}
