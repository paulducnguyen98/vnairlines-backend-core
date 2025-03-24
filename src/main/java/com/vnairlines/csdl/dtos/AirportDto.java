package com.vnairlines.csdl.dtos;

import java.util.UUID;

public class AirportDto {

    private UUID id;
    private String airportCode;
    private String airportName;
    private String city;
    private String country;

    public AirportDto(UUID id, String airportCode, String airportName, String city, String country) {
        super();
        this.id = id;
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.city = city;
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
