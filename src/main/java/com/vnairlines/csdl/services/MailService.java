package com.vnairlines.csdl.services;

public interface MailService {

    void sendBookingConfirmation(String toEmail, String bookingCode);
}
