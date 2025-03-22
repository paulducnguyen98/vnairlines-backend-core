package com.vnairlines.csdl.models;

import java.util.UUID;

public class SeatLayout {
    private UUID layoutId;
    private String layoutName;
    private String seatColumns;

    public UUID getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(UUID layoutId) {
        this.layoutId = layoutId;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
    }

    public String getSeatColumns() {
        return seatColumns;
    }

    public void setSeatColumns(String seatColumns) {
        this.seatColumns = seatColumns;
    }
}