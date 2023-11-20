package com.csis3275;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.csis3275.model.UserProfile;
import com.csis3275.model.UserServiceImpl;

import com.csis3275.model.User;
import com.csis3275.model.UserDAO;

@SpringBootApplication
public class FreelanceConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceConnectApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(UserServiceImpl repository)	{
		return (args) -> {
			repository.createProfile(new UserProfile("Jack Braun", "JackBraun@douglascollege.ca", "123456", "freelancer", null, null, null, null, null, null, null, null, null, null));
		};
	}
	
	@Bean
	CommandLineRunner seedUsers(UserDAO userDAO) {
		return (args) -> {
			userDAO.createUser(new User("Freelancer", "freelancer@connect.com", "password", "FREELANCER"));
			userDAO.createUser(new User("Employer", "employer@connect.com", "password", "EMPLOYER"));
		};
	}

			
//	@Bean
//	SpringTemplateEngine templateEngine() {
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.addDialect(new LayoutDialect());
//	    return templateEngine;
//	}
}
