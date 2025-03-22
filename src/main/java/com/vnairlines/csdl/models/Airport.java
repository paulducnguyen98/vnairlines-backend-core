package com.vnairlines.csdl.models;

import java.util.UUID;

public class Airport {

    private UUID airportId;
    private String airportCode;
    private String airportName;
    private String city;
    private String country;

    // Getters and setters
    public UUID getAirportId() {
        return airportId;
    }

    public void setAirportId(UUID airportId) {
        this.airportId = airportId;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
