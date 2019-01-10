package com.donationcenter.server.dto;

public class NotificationDto {
    private String name;

    public NotificationDto(String name) {
        this.name = name;
    }

    public NotificationDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
