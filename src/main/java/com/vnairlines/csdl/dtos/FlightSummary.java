package com.vnairlines.csdl.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlightSummary {

    private UUID flightId;
    private String flightNumber;
    private AirportSummary departureAirport;
    private AirportSummary arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public FlightSummary() {
        super();
    }

    public FlightSummary(UUID flightId, String flightNumber, AirportSummary departureAirport,
            AirportSummary arrivalAirport, LocalDateTime departureTime, LocalDateTime arrivalTime) {
        super();
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public AirportSummary getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(AirportSummary departureAirport) {
        this.departureAirport = departureAirport;
    }

    public AirportSummary getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(AirportSummary arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

}
