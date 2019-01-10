package com.donationcenter.server.controller;

import com.donationcenter.server.dto.RegisterDataDto;
import com.donationcenter.server.model.User;
import com.donationcenter.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @GetMapping("/login")
    //@CrossOrigin(origins = "http://localhost:4200")
    public User login(@RequestParam String username, @RequestParam String password){
        return loginService.login(username, password);
    }

    /*
    @GetMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<User> allUsers() {
        return loginService.allUsers();
    }*/

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDataDto userData){
        if(loginService.addDonator(userData.getUsername(), userData.getPassword(), userData.getName(), userData.getBloodType(), userData.getRh(), userData.getDateOfBirth(), userData.getPhoneNumber())) {
            return true;
        }
        return false;
    }
}