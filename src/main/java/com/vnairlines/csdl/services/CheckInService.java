package com.vnairlines.csdl.services;

import com.vnairlines.csdl.dtos.CheckInRequest;

public interface CheckInService {

    void performCheckIn(CheckInRequest request);
}
