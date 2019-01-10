package com.donationcenter.server.service;

import com.donationcenter.server.model.Donator;
import com.donationcenter.server.repository.AppointmentRepository;
import com.donationcenter.server.repository.DonatorRepository;
import com.donationcenter.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DonatorService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DonatorRepository donatorRepository;

    public List<Donator> findAllDonators() {
        try {
            return donatorRepository.findAll().stream().collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Donator findDonatorByUserId(int id) {
        try {
            Donator donator = donatorRepository.findByUserID(id);
            return donator;
        } catch (Exception e) {
            return null;
        }
    }

    public void updateDonator(Donator donator) {
        try{
            donatorRepository.updateBloodTypeAndIsActive(donator.getBloodType(), donator.getRh(), donator.getIsActive(), donator.getId());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Donator> filterByType(String type) {
        try {
            return donatorRepository.findAll().stream().filter(d -> (d.getBloodType().equals(type))).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Donator> filterByRh(String type) {
        try {
            return donatorRepository.findAll().stream().filter(d -> (d.getRh().equals(type))).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public List<Donator> filterByTypeAndRh(String type, String rh) {
        try {
            return donatorRepository.findAll().stream().filter(d -> (d.getRh().equals(rh)) && (d.getBloodType().equals(type))).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public void notifyDonator(String message) {
        try{
            FileWriter fstream = new FileWriter("log.txt",true);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(message);
            out.write(System.lineSeparator());
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
