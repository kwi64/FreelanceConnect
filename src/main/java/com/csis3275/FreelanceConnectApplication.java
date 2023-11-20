package com.csis3275;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.csis3275.model.User;
import com.csis3275.model.UserDAO;


@SpringBootApplication
public class FreelanceConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceConnectApplication.class, args);
	}
	
	@Bean
	CommandLineRunner seedUsers(UserDAO userDAO) {
		return (args) -> {
			userDAO.createUser(new User("Freelancer", "freelancer@connect.com", "password", "FREELANCER"));
			userDAO.createUser(new User("Employer", "employer@connect.com", "password", "EMPLOYER"));
		};
	}
}
