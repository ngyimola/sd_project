package com.donationcenter.server.repository;

import com.donationcenter.server.model.Donator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DonatorRepository extends JpaRepository<Donator, Long> {

    Donator findByUserID(int userID);

    @Transactional
    @Modifying
    @Query("update Donator d set d.bloodType =?1, d.rh =?2, d.isActive =?3 where d.id =?4")
    void updateBloodTypeAndIsActive(String newBloodType, String newRh, boolean newActivity, int id);
}
