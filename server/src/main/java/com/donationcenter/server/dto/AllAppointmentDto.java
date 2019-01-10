package com.donationcenter.server.dto;

import java.sql.Timestamp;

public class AllAppointmentDto {
    private int id;
    private Timestamp date;
    private String donatorName;
    private String bloodType;
    private String rh;

    public AllAppointmentDto(int id, Timestamp date, String donatorName, String bloodType, String rh) {
        this.id = id;
        this.date = date;
        this.donatorName = donatorName;
        this.bloodType = bloodType;
        this.rh = rh;
    }

    public AllAppointmentDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
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
}
