package com.vnairlines.csdl.models;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

public class Flight {
    private UUID flightId;
    private UUID airlineId;
    private String flightNumber;
    private UUID departureAirportId;
    private UUID arrivalAirportId;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private UUID aircraftId;
    private int seatCapacity;
    private BigDecimal basePrice;

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public UUID getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(UUID airlineId) {
        this.airlineId = airlineId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public UUID getDepartureAirportId() {
        return departureAirportId;
    }

    public void setDepartureAirportId(UUID departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public UUID getArrivalAirportId() {
        return arrivalAirportId;
    }

    public void setArrivalAirportId(UUID arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public UUID getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(UUID aircraftId) {
        this.aircraftId = aircraftId;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

}