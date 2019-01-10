package com.donationcenter.server.dto;

import com.donationcenter.server.model.Donator;

public class UpdateDonatorDto {

    private Donator donator;

    public UpdateDonatorDto(Donator donator) {
        this.donator = donator;
    }

    public UpdateDonatorDto() {
    }

    public Donator getDonator() {
        return donator;
    }

    public void setDonator(Donator donator) {
        this.donator = donator;
    }
}
