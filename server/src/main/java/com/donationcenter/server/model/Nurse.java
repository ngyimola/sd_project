package com.donationcenter.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nurse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userID;

    private String name;

    public Nurse(int userID, String name) {
        this.userID = userID;
        this.name = name;
    }

    public Nurse() {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
