package com.csis3275;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.csis3275.model.UserLocation;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserServiceImpl;

@SpringBootApplication
public class FreelanceConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceConnectApplication.class, args);
	}
	
	@Bean
	CommandLineRunner demo(UserServiceImpl repository)	{
		return (args) -> {
			repository.createProfile(new UserProfile("Jack Braun", "JackBraun@douglascollege.ca", "123456", "freelancer"));
			repository.addInfo(new UserLocation("123Street", null, "Vancouver", "BC", "Canada"));
		};
	}

			
//	@Bean
//	SpringTemplateEngine templateEngine() {
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.addDialect(new LayoutDialect());
//	    return templateEngine;
//	}
}
