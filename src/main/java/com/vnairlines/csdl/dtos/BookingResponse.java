package com.vnairlines.csdl.dtos;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.vnairlines.csdl.models.Booking;
import com.vnairlines.csdl.models.Passenger;

public class BookingResponse {

    private UUID bookingId;
    private UUID tripReferenceId;
    private String bookingCode;
    private String status; // PENDING, COMPLETED, FAILED
    private List<PassengerResponse> passengers;
    private PaymentResponse payment;

    public BookingResponse() {
        // TODO Auto-generated constructor stub
    }

    public BookingResponse(UUID bookingId, UUID tripReferenceId, String bookingCode, String status,
            List<PassengerResponse> passengers) {
        super();
        this.bookingId = bookingId;
        this.tripReferenceId = tripReferenceId;
        this.bookingCode = bookingCode;
        this.status = status;
        this.passengers = passengers;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PassengerResponse> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerResponse> passengers) {
        this.passengers = passengers;
    }

    public UUID getTripReferenceId() {
        return tripReferenceId;
    }

    public void setTripReferenceId(UUID tripReferenceId) {
        this.tripReferenceId = tripReferenceId;
    }

    public PaymentResponse getPayment() {
        return payment;
    }

    public void setPayment(PaymentResponse payment) {
        this.payment = payment;
    }

    public static BookingResponse fromEntity(Booking booking, List<Passenger> passengers) {
        List<PassengerResponse> passengerResponses = passengers.stream()
                .map(p -> new PassengerResponse(p.getPassengerId(), p.getFirstName(), p.getLastName(), p.getEmail(), 
                        p.getPhoneNumber(), p.getBirthDate(), p.getPassportNumber(), p.isMainContact(), p.getCreatedAt()))
                .collect(Collectors.toList());
        return new BookingResponse(booking.getBookingId(), booking.getTripReferenceId(), booking.getBookingCode(), booking.getStatus(),
                passengerResponses);
    }
}
