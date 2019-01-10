package com.donationcenter.server.dto;

public class AddDonationDto {
    private int id;
    private String quantity;

    public AddDonationDto(int id, String quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public AddDonationDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantity() {
        return quantity;
    }
}
