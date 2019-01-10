package com.donationcenter.server.dto;

public class AppointmentDto {
    private String username;
    private String date;
    private String time;

    public AppointmentDto() {
    }

    public AppointmentDto(String username, String date, String time) {
        this.username = username;
        this.date = date;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
