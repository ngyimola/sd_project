package com.donationcenter.server.controller;

import com.donationcenter.server.dto.*;
import com.donationcenter.server.model.Appointment;
import com.donationcenter.server.model.Donation;
import com.donationcenter.server.model.Donator;
import com.donationcenter.server.model.User;
import com.donationcenter.server.service.AppointmentService;
import com.donationcenter.server.service.DonationService;
import com.donationcenter.server.service.DonatorService;
import com.donationcenter.server.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
public class NurseController {

    private DonatorService donatorService;
    private LoginService loginService;
    private AppointmentService appointmentService;
    private DonationService donationService;

    public NurseController(DonatorService donatorService, LoginService loginService, AppointmentService appointmentService, DonationService donationService) {
        this.donatorService = donatorService;
        this.loginService = loginService;
        this.appointmentService = appointmentService;
        this.donationService = donationService;
    }

    @GetMapping("/allDonators")
    public List<Donator> allDonators() {
        return donatorService.findAllDonators();
    }

    @GetMapping("/filterByType")
    public List<Donator> donatorsByType(@RequestParam String bloodType) {
        return donatorService.filterByType(bloodType);
    }

    @GetMapping("/filterByRh")
    public List<Donator> donatorsByRh(@RequestParam String rh) {
        return donatorService.filterByRh(rh);
    }

    @GetMapping("/filterByTypeAndRh")
    public List<Donator> donatorsByTypeAndRh(@RequestParam String bloodType, @RequestParam String rh) {
        return donatorService.filterByTypeAndRh(bloodType, rh);
    }

    @PostMapping("/updateDonator")
    public void updateDonator(@RequestBody UpdateDonatorDto data) {
        donatorService.updateDonator(data.getDonator());
    }
/*
    @GetMapping("/allAppointments")
    public HashMap<Appointment, Donator> getAllAppointments() {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        HashMap<Appointment, Donator> dataSet = new HashMap<>();
        for(Appointment a : appointments) {
            dataSet.put(a, donatorService.findDonatorByUserId(a.getDonatorId()));
        }
        return dataSet;
    }*/

    @GetMapping("/allAppointments")
    public List<AllAppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentService.findAllAppointments();
        List<AllAppointmentDto> list = new ArrayList<>();
        for(Appointment a : appointments) {
            Donator donator = donatorService.findDonatorByUserId(a.getDonatorId());
            list.add(new AllAppointmentDto(a.getId(), a.getDate(), donator.getName(), donator.getBloodType(), donator.getRh()));
        }
        return list;
    }

    @PostMapping("/addDonation")
    public void addDonation(@RequestBody AddDonationDto data) {
        Appointment appointment = appointmentService.findAppointmentById(data.getId());
        donationService.addDonation(appointment.getDate(), appointment.getDonatorId(), Integer.parseInt(data.getQuantity()));
        appointmentService.deleteAppointment(Integer.toString(data.getId()));
    }

    @PostMapping("/notifyDonator")
    public void getHelpFromDonator(@RequestBody NotificationDto donator) {
        donatorService.notifyDonator("Request for donation sent for: " + donator.getName() + "\n");
    }

    @PostMapping("/sendResultForDonator")
    public void sendResultForDonator(@RequestParam int donatorId, @RequestParam String message) {
        donatorService.notifyDonator(message);
    }

    @PostMapping("/rejectAppointment")
    public void rejectAppointment(@RequestBody AppointmentToDelete appointmentToReject){
        appointmentService.deleteAppointment(Integer.toString(appointmentToReject.getId()));
    }
/*
    @GetMapping("/allDonations")
    public HashMap<Donator, Donation> allDonations() {
        List<Donation> donationList = donationService.findAllDonations();
        HashMap<Donator, Donation> dataMap = new HashMap<>();
        for (Donation d: donationList) {
            dataMap.put(donatorService.findDonatorByUserId(d.getDonatorId()), d);
        }
        return dataMap;
    }
*/

    @GetMapping("/allDonations")
    public List<AllDonationDto> allDonations() {
        List<AllDonationDto> list = new ArrayList<>();
        List<Donation> donationList = donationService.findAllDonations();
        for (Donation d: donationList) {
            Donator donator = donatorService.findDonatorByUserId(d.getDonatorId());
            list.add(new AllDonationDto(Integer.toString(d.getId()), d.getDate().toString(),donator.getName() ,donator.getBloodType(), donator.getRh(), d.getState(), d.getResults()));
        }
        return list;
    }

    @PostMapping("/updateDonation")
    public void updateDonation(@RequestBody UpdataDonationDto data) {
        Donation donation = donationService.findDonationById(data.getDonationId());
        donation.setState(data.getState());
        donation.setResults(data.getMessage());
        donationService.updateDonation(donation);
    }
}
