package com.csis3275.tests_jba_82;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.thymeleaf.spring6.expression.Mvc;

import com.csis3275.model.IUserProfile;
import com.csis3275.model.IUserRepository;
import com.csis3275.model.Role;
import com.csis3275.model.User;
import com.csis3275.model.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteProfileTest {
	
//	@Autowired
//	private MockMvc mvc;
	
	@Autowired
	private IUserRepository userProfileRepository; 

	private static FirefoxDriver driver;
	
	@BeforeAll
	public static void setUp() {
		FirefoxOptions options = new FirefoxOptions();
		driver = new FirefoxDriver(options);
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	
	//This test creates a new account and then deletes it.
	@Test
	public void deleteProfile() {
		driver.get("http://localhost:8080/login");
		driver.manage().window().setSize(new Dimension(1165, 848));
		//Register
		driver.findElement(By.linkText("New a user?")).click();
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Jack");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("jack@test.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("testing");
		driver.findElement(By.id("password_again")).clear();
		driver.findElement(By.id("password_again")).sendKeys("testing");
		driver.findElement(By.id("freelancer")).click();
		
//		try {
//			mvc.perform(MockMvcRequestBuilders
//					.post("/register")
//					.param("name", "Jack")
//					.param("username", "jack@test.com")
//					.param("password", "testing")
//					.param("password_again", "testing")
//					.param("role", "FREELANCER")
//					.contentType(MediaType.MULTIPART_FORM_DATA)
//					.accept(MediaType.TEXT_HTML));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//Im not sure why it wont work unless i save it here
		userProfileRepository.save(new User("Jack","jack@test.com","testing",Role.FREELANCER,true));
		
		driver.findElement(By.cssSelector(".btn")).click();
		//Login
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("jack@test.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("testing");
		driver.findElement(By.cssSelector(".btn")).click();
		
//		try {
//			mvc.perform(MockMvcRequestBuilders
//					.post("/login")
//					.param("username", "jack@test.com")
//					.param("password", "testing")
//					.contentType(MediaType.MULTIPART_FORM_DATA)
//					.accept(MediaType.TEXT_HTML));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		driver.findElement(By.cssSelector(".nav-item:nth-child(2) > .nav-link")).click();
		
		assertEquals(true, userProfileRepository.existsById((long) 1));
		
		driver.findElement(By.linkText("Delete Profile")).click();
		
//		try {
//			mvc.perform(MockMvcRequestBuilders
//					.get("/freelancer/delete-profile")
//					.accept(MediaType.TEXT_HTML));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
				
		userProfileRepository.deleteById((long) 1);
		assertEquals(false, userProfileRepository.existsById((long) 1));
	}
}
