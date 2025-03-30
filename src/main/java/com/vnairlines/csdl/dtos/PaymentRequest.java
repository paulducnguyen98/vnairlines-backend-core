package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentRequest {

    private UUID tripReferenceId;
    private UUID bookingId;
    private BigDecimal amount;
    private String paymentMethod; // e.g. VNPAY, PAYPAL
    private String status;

    public PaymentRequest() {
        // TODO Auto-generated constructor stub
    }

    public PaymentRequest(UUID tripReferenceId, UUID bookingId, BigDecimal amount, String paymentMethod, String status) {
        super();
        this.tripReferenceId = tripReferenceId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public UUID getBookingId() {
        return bookingId;
    }
}
