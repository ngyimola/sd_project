package com.donationcenter.server.dto;

public class RegisterDataDto {
    private String username;
    private String password;
    private String name;
    private String bloodType;
    private String rh;
    private String dateOfBirth;
    private String phoneNumber;

    public RegisterDataDto() {
    }

    public RegisterDataDto(String username, String password, String name, String bloodType, String rh, String dateOfBirth, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.bloodType = bloodType;
        this.rh = rh;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }
}
