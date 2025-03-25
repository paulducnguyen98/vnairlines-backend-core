package com.vnairlines.csdl.services;

import java.util.UUID;

import com.vnairlines.csdl.dtos.PaymentDto;

public interface PaymentService {
    PaymentDto getPaymentByBookingId(UUID bookingId);

    PaymentDto getPaymentById(UUID paymentId);

    void createPayment(PaymentDto paymentDto);

    void updatePayment(UUID paymentId, PaymentDto paymentDto);

    void deletePayment(UUID paymentId);
}
