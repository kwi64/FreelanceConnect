package com.csis3275.tests_jba_82;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.csis3275.FreelanceConnectApplication;
import com.csis3275.model.IUserRepository;
import com.csis3275.model.IUserWorkExperience;
import com.csis3275.model.Role;
import com.csis3275.model.User;
import com.csis3275.model.UserExperienceServiceImpl;
import com.csis3275.model.UserPrincipal;
import com.csis3275.model.UserService;
import com.csis3275.model.UserWorkExperience;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
@SpringBootTest
@AutoConfigureMockMvc
class WorkExperienceTest {

	int currUser = 0;
	Long expCount = (long) 0;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private IUserWorkExperience experienceRepository;
	
	@Autowired
	private UserExperienceServiceImpl experienceService;

	private static FirefoxDriver driver;

	@BeforeAll
	public static void setUp() {
		FirefoxOptions options = new FirefoxOptions();
		driver = new FirefoxDriver(options);
		
		String password = "password";
        UserPrincipal userPrincipal = new UserPrincipal(1, "Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userPrincipal, password, userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void workExperience() {
		//this is just here to create the account
		try {
			mvc.perform(MockMvcRequestBuilders
					.post("/register")
					.param("name", "Freelancer")
					.param("username", "freelancer@connect.com")
					.param("password", "password")
					.param("password_again", "password")
					.param("role", "FREELANCER")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.TEXT_HTML));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		User user = new User("Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true);
//		userRepository.save(user);
		driver.get("http://localhost:8080/login");
		driver.manage().window().setSize(new Dimension(1165, 851));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("freelancer@connect.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.cssSelector(".btn")).click();

		try {
			mvc.perform(MockMvcRequestBuilders.post("/login")
					.param("username", "freelancer@connect.com")
					.param("password", "password")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.TEXT_HTML));
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.findElement(By.cssSelector(".nav-item:nth-child(2) > .nav-link")).click();
		driver.findElement(By.linkText("Add Experience")).click();
		driver.findElement(By.id("title")).click();
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Experience 1");
		driver.findElement(By.id("company")).clear();
		driver.findElement(By.id("company")).sendKeys("Company 1");
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("New West");
		driver.findElement(By.id("hireDate")).click();
		driver.findElement(By.id("hireDate")).clear();
		driver.findElement(By.id("hireDate")).sendKeys("2023-12-05");
		driver.findElement(By.id("quitDate")).click();
		driver.findElement(By.id("quitDate")).clear();
		driver.findElement(By.id("quitDate")).sendKeys("2023-12-08");
		driver.findElement(By.cssSelector(".btn-primary")).click();
		
		try {
			mvc.perform(MockMvcRequestBuilders.post("/freelancer/add-experience")
					.param("title", "Experience 1")
					.param("company", "Company 1")
					.param("location", "New West")
					.param("hireDate", "2023-12-05")
					.param("quitDate", "2023-12-08")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.TEXT_HTML));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
//		currUser++;
//
//		User user = userService
//				.createUser(new User("freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true));
//		userRepository.save(user);
//
//		experienceRepository.save(new UserWorkExperience(user, "Experience 1", "Company 1", "New West",
//				LocalDate.parse("2023-12-05"), LocalDate.parse("2023-12-08"), false));

		// First experience now submitted

		driver.findElement(By.linkText("Add Experience")).click();
		driver.findElement(By.id("title")).click();
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Experience 2");
		driver.findElement(By.id("company")).click();
		driver.findElement(By.id("company")).clear();
		driver.findElement(By.id("company")).sendKeys("Company 2");
		driver.findElement(By.id("location")).click();
		driver.findElement(By.id("location")).clear();
		driver.findElement(By.id("location")).sendKeys("Burnaby");
		driver.findElement(By.id("hireDate")).click();
		driver.findElement(By.id("hireDate")).clear();
		driver.findElement(By.id("hireDate")).sendKeys("2023-12-04");
		driver.findElement(By.id("quitDate")).click();
		driver.findElement(By.id("quitDate")).clear();
		driver.findElement(By.id("quitDate")).sendKeys("2023-12-11");
		driver.findElement(By.id("currentlyWorking")).click();
		driver.findElement(By.cssSelector(".btn-primary")).click();
		
		try {
			mvc.perform(MockMvcRequestBuilders.post("/freelancer/add-experience")
					.param("title", "Experience 2")
					.param("company", "Company 2")
					.param("location", "Burnaby")
					.param("hireDate", "2023-12-04")
					.param("quitDate", "2023-12-11")
					.contentType(MediaType.MULTIPART_FORM_DATA)
					.accept(MediaType.TEXT_HTML));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		currUser++;
//
//		experienceRepository.save(new UserWorkExperience(user, "Experience 2", "Company 2", "Burnaby",
//				LocalDate.parse("2023-12-04"), LocalDate.parse("2023-12-11"), true));
//		expCount = experienceRepository.count();

		assertEquals(true, experienceRepository.existsById((long) 1));
		// (currUser - (expCount+1)));

		// Deleting experience 1
		
		try {
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/delete-experience/1")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML));
	} catch (Exception e) {
		e.printStackTrace();
	}
		
		driver.findElement(By.linkText("Delete This Experience")).click();
//		experienceRepository.deleteById((long) 1);

		assertEquals(false, experienceRepository.existsById((long) 1));
		// experienceRepository.existsById(currUser - expCount));
	}
}
