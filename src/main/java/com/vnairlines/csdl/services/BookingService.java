package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.BookingRequest;
import com.vnairlines.csdl.dtos.BookingResponse;

public interface BookingService {
    List<BookingResponse> createRoundTripBooking(BookingRequest request);

    BookingResponse getBookingDetails(UUID bookingId);
}