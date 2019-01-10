package com.donationcenter.server.controller;

import com.donationcenter.server.dto.AppointmentDto;
import com.donationcenter.server.dto.AppointmentToDelete;
import com.donationcenter.server.model.Appointment;
import com.donationcenter.server.model.Donation;
import com.donationcenter.server.model.Donator;
import com.donationcenter.server.model.User;
import com.donationcenter.server.service.AppointmentService;
import com.donationcenter.server.service.DonationService;
import com.donationcenter.server.service.DonatorService;
import com.donationcenter.server.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class DonatorController {

    private DonatorService donatorService;
    private LoginService loginService;
    private AppointmentService appointmentService;
    private DonationService donatonService;

    public DonatorController(DonatorService donatorService, LoginService loginService, AppointmentService appointmentService, DonationService donatonService) {
        this.donatorService = donatorService;
        this.loginService = loginService;
        this.appointmentService = appointmentService;
        this.donatonService = donatonService;
    }

    @GetMapping("/donatorData")
    public Donator getDonatorDataOfUser(@RequestParam String username) {
        User user = loginService.findUserByUsername(username);
        Donator donator = donatorService.findDonatorByUserId(user.getId());
        List<Donation> donations = donatonService.findDonationdForDonator(user.getId());
        boolean paused = false;
        Date current = new Date();
        long threeMonths = 3 * 30 * 24 * 60 * 60 * 1000;
        for(Donation d: donations) {
            if(current.getTime() - d.getDate().getTime() < threeMonths) {
                paused = true;
            }
        }
        donator.setActive(paused);
        return donator;
    }

    @GetMapping("/getAvailableAppointments")
    public List<String> getAvailableAppointments(@RequestParam String date) {
        return appointmentService.generateAppointments(date);
    }

    @PostMapping("/createAppointment")
    public void addAppointment(@RequestBody AppointmentDto appointmentData) {
        User user = loginService.findUserByUsername(appointmentData.getUsername());
        appointmentService.addAppointment(appointmentData.getDate().substring(0, appointmentData.getDate().indexOf('T')) + " " + appointmentData.getTime(), user.getId());
    }

    @GetMapping("/allAppointmentsForUser")
    public List<Appointment> viewOwnAppointments(@RequestParam String username){
        User user = loginService.findUserByUsername(username);
        return appointmentService.getAppointmentsForDonator(user.getId());
    }

    @PostMapping("/deleteAppointment")
    public void deleteAppointment(@RequestBody AppointmentToDelete appointmentToDelete){
        appointmentService.deleteAppointment(Integer.toString(appointmentToDelete.getId()));
    }

    @GetMapping("/getDonationsForUser")
    public List<Donation> viewDonations(@RequestParam String username) {
        User user = loginService.findUserByUsername(username);
        return donatonService.findDonationdForDonator(user.getId());
    }

}
