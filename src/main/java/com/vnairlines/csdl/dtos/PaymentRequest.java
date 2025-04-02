package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRequest {

    private UUID tripReferenceId;
    private UUID bookingId;
    private BigDecimal amount;
    private String paymentMethod; // e.g. VNPAY, PAYPAL

    public PaymentRequest() {
        // TODO Auto-generated constructor stub
    }

    public PaymentRequest(UUID tripReferenceId, UUID bookingId, BigDecimal amount, String paymentMethod) {
        super();
        this.tripReferenceId = tripReferenceId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

    public UUID getTripReferenceId() {
        return tripReferenceId;
    }

    public void setTripReferenceId(UUID tripReferenceId) {
        this.tripReferenceId = tripReferenceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }
}
