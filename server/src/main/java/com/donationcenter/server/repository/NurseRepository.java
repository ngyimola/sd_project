package com.donationcenter.server.repository;

import com.donationcenter.server.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Nurse findByUserID(int userID);
}
