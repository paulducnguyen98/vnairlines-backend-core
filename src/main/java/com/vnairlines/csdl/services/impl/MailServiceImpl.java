package com.vnairlines.csdl.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.services.MailService;

@Service
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendBookingConfirmation(String toEmail, String bookingCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Booking Confirmation - VNAirlines");
        message.setText("Thank you for your payment!\n\nYour booking code is: " + bookingCode +
                        "\nPlease use this code to manage your booking or check in.");
        mailSender.send(message);
    }
}
