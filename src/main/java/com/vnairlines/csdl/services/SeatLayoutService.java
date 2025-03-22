package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.SeatLayout;

public interface SeatLayoutService {
    List<SeatLayout> getAllLayouts();

    SeatLayout getLayoutById(UUID id);

    SeatLayout getLayoutByName(String layoutName);

    void createLayout(SeatLayout layout);

    void updateLayout(UUID id, SeatLayout layout);

    void deleteLayout(UUID id);
}