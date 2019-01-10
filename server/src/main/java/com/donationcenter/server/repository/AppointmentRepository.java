package com.donationcenter.server.repository;

import com.donationcenter.server.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findById(int id);
}
