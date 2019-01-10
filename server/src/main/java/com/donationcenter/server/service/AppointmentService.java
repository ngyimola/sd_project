package com.donationcenter.server.service;

import com.donationcenter.server.model.Appointment;
import com.donationcenter.server.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    public boolean addAppointment(String date, int donatorId) {
        try {
            Timestamp adjustedDate = Timestamp.valueOf(date);
            Appointment appointment = new Appointment();
            appointment.setDate(adjustedDate);
            appointment.setDonatorId(donatorId);
            appointmentRepository.save(appointment);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<Appointment> getAppointmentsForDonator(int donatorId) {
        try {
            List<Appointment> appointments = appointmentRepository.findAll().stream().filter(a-> a.getDonatorId() == donatorId).collect(Collectors.toList());
            return appointments;
        } catch (Exception e){
            return null;
        }
    }

    public List<Appointment> findAllAppointments() {
        try {
            return appointmentRepository.findAll().stream().collect(Collectors.toList());
        } catch (Exception e){
            return null;
        }
    }

    public boolean deleteAppointment(String id) {
        try {

            Appointment appointment = appointmentRepository.findById(Integer.parseInt(id));
            appointmentRepository.delete(appointment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> generateAppointments(String date) {
        List<String> appointments = new ArrayList<>();
        for (int i = 9; i < 14; i++) {
            for(int j = 0; j<=50; j+=5) {
                String newApp;
                if((i < 10) && (j< 10)) {
                    newApp = "0" + Integer.toString(i) + ":" + "0" + Integer.toString(j) + ":00";
                }
                else if ((i<10)){
                    newApp = "0" + Integer.toString(i) + ":" + Integer.toString(j) + ":00";
                }
                else if((j<10)) {
                    newApp = Integer.toString(i) + ":" + "0" + Integer.toString(j) + ":00";
                }
                else {
                    newApp = Integer.toString(i) + ":" + Integer.toString(j) + ":00";
                }

                List<Appointment> app = findAllAppointments();
                boolean isMatching = false;
                for (Appointment a : app) {
                    if(a.getDate().toString().equals(date + " " + newApp + ".0")) {
                        isMatching =  true;
                    }
                }

                if(!isMatching) {
                    appointments.add(newApp);
                }

            }
        }
        return appointments;
    }

    public Appointment findAppointmentById(int id) {
        try{
            return appointmentRepository.findById(id);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}