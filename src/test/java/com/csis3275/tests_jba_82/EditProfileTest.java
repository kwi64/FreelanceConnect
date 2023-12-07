package com.csis3275.tests_jba_82;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;

import com.csis3275.model.IUserRepository;
import com.csis3275.model.UserService;

class EditProfileTest {
	
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
	
	//Testing to ensure that after entering account info it is properly displayed in manage profile
	@Test
	public void editAccountInfo() {
		driver.get("http://localhost:8080/login");
		driver.manage().window().setSize(new Dimension(1165, 848));
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("freelancer@connect.com");
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.cssSelector(".btn")).click();
		driver.findElement(By.cssSelector(".nav-item:nth-child(2) > .nav-link")).click();
		driver.findElement(By.linkText("Edit Profile")).click();
		driver.findElement(By.id("address1")).click();
		driver.findElement(By.id("address1")).clear();
		driver.findElement(By.id("address1")).sendKeys("My House");
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("city")).sendKeys("Vancouver");
		driver.findElement(By.id("province")).clear();
		driver.findElement(By.id("province")).sendKeys("BC");
		driver.findElement(By.id("country")).clear();
		driver.findElement(By.id("country")).sendKeys("Canada");
		driver.findElement(By.id("skills")).clear();
		driver.findElement(By.id("skills")).sendKeys("Java, Selenium, Coding");
		driver.findElement(By.cssSelector(".btn:nth-child(9)")).click();
		driver.findElement(By.linkText("Edit Profile")).click();
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("Jack");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
		
		assertEquals("Name: Jack\n"
				+ "Email: freelancer@connect.com\n"
				+ "Address 1: My House\n"
				+ "Address 2:\n"
				+ "City: Vancouver\n"
				+ "Province: BC\n"
				+ "Country: Canada\n"
				+ "Skills: Java, Selenium, Coding",
				driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div/div[2]/div")).getText());
	}
}
