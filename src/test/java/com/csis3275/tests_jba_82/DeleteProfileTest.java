package com.csis3275.tests_jba_82;

import static org.junit.jupiter.api.Assertions.*;
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

import com.csis3275.model.IUserRepository;
import com.csis3275.model.User;
import com.csis3275.model.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class DeleteProfileTest {
	
	@Autowired
	private UserService userService;
	
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
		
		driver.findElement(By.cssSelector(".btn")).click();
		//Login
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("jack@test.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("testing");
		driver.findElement(By.cssSelector(".btn")).click();
		driver.findElement(By.cssSelector(".nav-item:nth-child(2) > .nav-link")).click();
		
		
//		User user = userService.findUserByUsername("jack@test.com");
//		Long userId = user.getId();
		
		
		driver.findElement(By.linkText("Delete Profile")).click();
		
		assertEquals(false, userProfileRepository.existsById((long) 3));
	}
}
