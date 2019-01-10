package com.donationcenter.server;

import com.donationcenter.server.model.User;
import com.donationcenter.server.repository.UserRepository;
import com.donationcenter.server.service.DonationService;
import com.donationcenter.server.service.DonatorService;
import com.donationcenter.server.service.LoginService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
/*
	@Bean
	ApplicationRunner init(LoginService loginService, DonationService donationService) {
		return args -> {
			Stream.of("a", "b", "c", "d").forEach(name -> {
				loginService.addDonator(name, name, name, "A", "+", "1990-01-01", "0456785623");
			});
			//donationService.addDonation("1990-01-01", 1, 400);
		};
	}*/
}

