package com.vnairlines.csdl.services;

import java.util.UUID;

import com.vnairlines.csdl.dtos.PaymentRequest;
import com.vnairlines.csdl.dtos.PaymentResponse;

public interface PaymentService {
    PaymentResponse getPaymentByBookingId(UUID bookingId);

    PaymentResponse getPaymentById(UUID paymentId);

    void createPayment(PaymentRequest paymentDto);
}
