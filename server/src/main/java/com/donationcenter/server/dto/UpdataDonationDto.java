package com.donationcenter.server.dto;

public class UpdataDonationDto {

    private int donationId;
    private String state;
    private String message;

    public UpdataDonationDto(int donationId, String state, String message) {
        this.donationId = donationId;
        this.state = state;
        this.message = message;
    }

    public UpdataDonationDto() {
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
