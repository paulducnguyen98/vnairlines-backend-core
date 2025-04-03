package com.vnairlines.csdl.dtos;

import java.util.List;

public class TripBookingResponse {

    private List<BookingResponse> bookings;
    private double totalPrice;

    public TripBookingResponse(List<BookingResponse> bookings, double totalPrice) {
        this.bookings = bookings;
        this.totalPrice = totalPrice;
    }

    public List<BookingResponse> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingResponse> bookings) {
        this.bookings = bookings;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}