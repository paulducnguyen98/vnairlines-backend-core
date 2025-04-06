package com.vnairlines.csdl.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class MileRedemptionDto {
    private UUID redemptionId;
    private Integer milesUsed;
    private String redeemedFor;
    private UUID relatedTicketId;
    private LocalDateTime redeemedAt;

    public UUID getRedemptionId() {
        return redemptionId;
    }

    public void setRedemptionId(UUID redemptionId) {
        this.redemptionId = redemptionId;
    }

    public Integer getMilesUsed() {
        return milesUsed;
    }

    public void setMilesUsed(Integer milesUsed) {
        this.milesUsed = milesUsed;
    }

    public String getRedeemedFor() {
        return redeemedFor;
    }

    public void setRedeemedFor(String redeemedFor) {
        this.redeemedFor = redeemedFor;
    }

    public UUID getRelatedTicketId() {
        return relatedTicketId;
    }

    public void setRelatedTicketId(UUID relatedTicketId) {
        this.relatedTicketId = relatedTicketId;
    }

    public LocalDateTime getRedeemedAt() {
        return redeemedAt;
    }

    public void setRedeemedAt(LocalDateTime redeemedAt) {
        this.redeemedAt = redeemedAt;
    }

}
