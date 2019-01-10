package com.donationcenter.server.repository;

import com.donationcenter.server.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    Donation findById(int id);

    @Transactional
    @Modifying
    @Query("update Donation d set d.state =?1, d.results =?2 where d.id =?3")
    void updateStateAndResults(String newState, String newResults, int id);

}
