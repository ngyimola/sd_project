package com.donationcenter.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Donator{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userID;

    private String name;

    private String bloodType;

    private String rh;

    private Date dateOfBirth;

    private String phoneNumber;

    private boolean isActive;

    /*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "donations")
    private Set<Donation> donations;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "appointments")
    private Set<Appointment> appointments;
*/
    public Donator(int userID, String name, String bloodType, String rh, Date dateOfBirth, String phoneNumber, boolean isActive) {
        this.userID = userID;
        this.name = name;
        this.bloodType = bloodType;
        this.rh = rh;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
    }

    public Donator() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }
}
