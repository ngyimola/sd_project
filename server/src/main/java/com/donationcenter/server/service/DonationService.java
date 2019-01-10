package com.donationcenter.server.service;

import com.donationcenter.server.model.Donation;
import com.donationcenter.server.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    public List<Donation> findDonationdForDonator(int id) {
        try {
            return donationRepository.findAll().stream().filter(d-> d.getDonatorId() == id).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void addDonation(Date date, int donatorId, int quantity) {
        try {
            Donation donation = new Donation();
            donation.setDonatorId(donatorId);
            donation.setDate(date);
            donation.setQuantity(quantity);
            donation.setState("processing");
            donationRepository.save(donation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Donation> findAllDonations(){
        try{
            return donationRepository.findAll();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Donation findDonationById(int id) {
        try {
            return donationRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void updateDonation(Donation donation) {
        try {
            donationRepository.updateStateAndResults(donation.getState(), donation.getResults(), donation.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
