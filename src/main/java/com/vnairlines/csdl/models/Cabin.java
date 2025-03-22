package com.vnairlines.csdl.models;

import java.util.UUID;

public class Cabin {
    private UUID cabinId;
    private UUID aircraftId;
    private String cabinClass; // FIRST, BUSINESS, ECONOMY
    private int startRow;
    private int endRow;
    private UUID layoutId;
    private int cabinCapacity;

    public UUID getCabinId() {
        return cabinId;
    }

    public void setCabinId(UUID cabinId) {
        this.cabinId = cabinId;
    }

    public UUID getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(UUID aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getCabinClass() {
        return cabinClass;
    }

    public void setCabinClass(String cabinClass) {
        this.cabinClass = cabinClass;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public UUID getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(UUID layoutId) {
        this.layoutId = layoutId;
    }

    public int getCabinCapacity() {
        return cabinCapacity;
    }

    public void setCabinCapacity(int cabinCapacity) {
        this.cabinCapacity = cabinCapacity;
    }
}