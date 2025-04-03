package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.BookingRequest;
import com.vnairlines.csdl.dtos.BookingResponse;
import com.vnairlines.csdl.dtos.SeatAssignmentRequest;

public interface BookingService {
    List<BookingResponse> createRoundTripBooking(BookingRequest request);

    BookingResponse getBookingDetails(UUID bookingId);

    void assignSeatsToPassengers(SeatAssignmentRequest request);
}