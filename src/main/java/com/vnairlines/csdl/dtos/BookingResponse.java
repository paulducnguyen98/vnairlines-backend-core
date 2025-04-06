package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private String ticketClass;
    private BigDecimal price;
    private FlightSummary flight;
    private String contactFirstName;
    private String contactLastName;
    private String contactEmail;
    private String contactPhone;

    public BookingResponse() {
        // TODO Auto-generated constructor stub
    }

    public BookingResponse(UUID bookingId, UUID tripReferenceId, String bookingCode, String status,
            List<PassengerResponse> passengers, String ticketClass, BigDecimal price) {
        super();
        this.bookingId = bookingId;
        this.tripReferenceId = tripReferenceId;
        this.bookingCode = bookingCode;
        this.status = status;
        this.passengers = passengers;
        this.ticketClass = ticketClass;
        this.price = price;
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

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setFlight(FlightSummary flight) {
        this.flight = flight;
    }

    public FlightSummary getFlight() {
        return flight;
    }

    public static BookingResponse fromEntity(Booking booking, List<Passenger> passengers, String ticketClass) {
        List<PassengerResponse> passengerResponses = passengers.stream()
                .map(p -> new PassengerResponse(p.getPassengerId(), p.getFirstName(), p.getLastName(), p.getEmail(),
                        p.getPhoneNumber(), p.getBirthDate(), p.getPassportNumber(), p.getCitizenId(),
                        p.isMainContact(), p.getCreatedAt()))
                .collect(Collectors.toList());
        return new BookingResponse(booking.getBookingId(), booking.getTripReferenceId(), booking.getBookingCode(),
                booking.getStatus(), passengerResponses, ticketClass, booking.getTotalPrice());
    }
}
