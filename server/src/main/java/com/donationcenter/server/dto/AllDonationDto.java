package com.donationcenter.server.dto;

public class AllDonationDto {

    private String id;
    private String date;
    private String donatorName;
    private String bloodType;
    private String rh;
    private String state;
    private String message;

    public AllDonationDto(String id, String date, String donatorName, String bloodType, String rh, String state, String message) {
        this.id = id;
        this.date = date;
        this.donatorName = donatorName;
        this.bloodType = bloodType;
        this.rh = rh;
        this.state = state;
        this.message = message;
    }

    public AllDonationDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDonatorName() {
        return donatorName;
    }

    public void setDonatorName(String donatorName) {
        this.donatorName = donatorName;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
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


