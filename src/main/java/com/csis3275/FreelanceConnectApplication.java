package com.csis3275;

import java.time.LocalDate;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;
import com.csis3275.model.UserServiceImpl;
import com.csis3275.model.UserWorkExperience;
import com.csis3275.model.IUserProfile;
import com.csis3275.model.IUserRepository;
import com.csis3275.model.IUserWorkExperience;
import com.csis3275.model.Job;
import com.csis3275.model.JobApplication;
import com.csis3275.model.JobApplicationService;
import com.csis3275.model.JobApplicationSkill;
import com.csis3275.model.JobApplicationSkillService;
import com.csis3275.model.JobApplicationStatus;
import com.csis3275.model.JobApplicationWorkExperience;
import com.csis3275.model.JobApplicationWorkExperienceService;
import com.csis3275.model.JobService;
import com.csis3275.model.Role;
import com.csis3275.model.User;

@SpringBootApplication
public class FreelanceConnectApplication {
	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = 
		SpringApplication.run(FreelanceConnectApplication.class, args);
		
		IUserRepository userProfileRepository = configurableApplicationContext.getBean(IUserProfile.class);
		UserService userDAO = configurableApplicationContext.getBean(UserService.class);
		
		User user1 = userDAO.createUser(new UserProfile(
				"Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true,null,null,null,null,null,null));
		
		User user2 = userDAO.createUser(new UserProfile(
				"Employer", "employer@connect.com", "password", Role.EMPLOYER, true,null,null,null,null,null,null));
		userProfileRepository.save(user1);
		userProfileRepository.save(user2);	
	}
	
	
	//@Bean
	//CommandLineRunner seedUsers(UserService userDAO) {
	//	return (args) -> {
	//   	Replaced this part and placed in main
	//		userDAO.createUser(new User("Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true));
	//		userDAO.createUser(new User("Employer", "employer@connect.com", "password", Role.EMPLOYER, true));
	//	};
	//}

	@Bean
	CommandLineRunner seedJobs(JobService jobService) {
		return (args) -> {
			Job j1 = new Job(1L, "Software Engineer I", "EPASS Software", "New West, CA", "Lorem Ipsum", LocalDate.of(2023, 11, 23), LocalDate.of(2023, 12, 31), "12");
			Job j2 = new Job(2L, "Software Engineer II", "EPASS Software", "New West, CA", "Lorem Ipsum", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 12, 31), "15");
			Job j3 = new Job(3L, "Software Engineer III", "EPASS Software", "New West, CA", "Lorem Ipsum", LocalDate.of(2022, 11, 22), LocalDate.of(2023, 12, 31), "15");
			
			jobService.createJob(j1);
			jobService.createJob(j2);
			jobService.createJob(j3);
		};
	}
	
	@Bean
	CommandLineRunner seedJobApplications(JobApplicationService jobApplicationService, JobApplicationWorkExperienceService experienceService, JobApplicationSkillService skillService) {
		return (args) -> {
			JobApplication ja1 = new JobApplication(1, 1, "Freelancer Full Name", "addrL1", "addrL2", "New West", "BC", "CA", null); 
			JobApplication ja2 = new JobApplication(1, 2, "Freelancer Full Name", "addrL1", "addrL2", "New West", "BC", "CA", JobApplicationStatus.INPROGRESS);
			
			jobApplicationService.createJobApplication(ja1);
			jobApplicationService.createJobApplication(ja2);
			
			skillService.createSkill(ja1, new JobApplicationSkill("Java"));
			skillService.createSkill(ja1, new JobApplicationSkill("c#"));
			skillService.createSkill(ja1, new JobApplicationSkill("JavaScript"));
			
			experienceService.createExperience(ja1, new JobApplicationWorkExperience(ja1, "Pos1", "Comp1", "NewWest,BC,CA", LocalDate.of(2020, 1, 8), LocalDate.of(2021, 1, 8)));
			experienceService.createExperience(ja1, new JobApplicationWorkExperience(ja1, "Pos2", "Comp2", "NewWest,BC,CA", LocalDate.of(2021, 1, 8), LocalDate.of(2022, 1, 8)));
			
			skillService.createSkill(ja2, new JobApplicationSkill("Customer Service"));
			skillService.createSkill(ja2, new JobApplicationSkill("Sales"));
			
			experienceService.createExperience(ja2, new JobApplicationWorkExperience(ja2, "Pos3", "Comp3", "NewWest,BC,CA", LocalDate.of(2022, 1, 8), LocalDate.of(2023, 1, 8)));
		};
	}
}
