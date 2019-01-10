package com.donationcenter.server.service;

import com.donationcenter.server.model.Donator;
import com.donationcenter.server.model.User;
import com.donationcenter.server.repository.DonatorRepository;
import com.donationcenter.server.repository.NurseRepository;
import com.donationcenter.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    DonatorRepository donatorRepository;
    @Autowired
    NurseRepository nurseRepository;

    public User login(String username, String password) {
        try {
            User user = userRepository.findByUsernameAndPassword(username, password);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addDonator(String username, String password, String name, String bloodType, String rh, String dateOfBirth, String phoneNumber) {
        try {
            Date birth = new SimpleDateFormat("yyyy-MM-dd").parse(dateOfBirth);
            User user = new User(username, password, "donator");
            userRepository.save(user);
            int id = login(username, password).getId();
            Donator donator = new Donator(id, name, bloodType, rh, birth, phoneNumber, true);
            donatorRepository.save(donator);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public User findUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> allUsers(){
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }



}
