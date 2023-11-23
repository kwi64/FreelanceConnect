package com.csis3275;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;
import com.csis3275.model.UserServiceImpl;

import com.csis3275.model.Role;
import com.csis3275.model.User;

@SpringBootApplication
public class FreelanceConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceConnectApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(UserServiceImpl repository)	{
		return (args) -> {
			repository.createProfile(new UserProfile("Jack Braun", "JackBraun@douglascollege.ca", "123456", true, null, null, null, null, null, null, null, null, null, null));
		};
	}
	
	@Bean
	CommandLineRunner seedUsers(UserService userDAO) {
		return (args) -> {
			userDAO.createUser(new User("Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true));
			userDAO.createUser(new User("Employer", "employer@connect.com", "password", Role.EMPLOYER, true));
		};
	}

			
//	@Bean
//	SpringTemplateEngine templateEngine() {
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.addDialect(new LayoutDialect());
//	    return templateEngine;
//	}
}
