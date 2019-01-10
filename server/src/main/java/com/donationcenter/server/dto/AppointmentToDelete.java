package com.donationcenter.server.dto;

public class AppointmentToDelete {
    int id;

    public AppointmentToDelete() {
    }

    public AppointmentToDelete(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
