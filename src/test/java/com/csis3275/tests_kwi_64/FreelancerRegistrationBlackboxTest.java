package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FreelancerRegistrationBlackboxTest {
	private static FirefoxDriver driver;

	@BeforeAll
	public static void setUp() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-private");
		driver = new FirefoxDriver(options);
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void freelancerRegistration() throws InterruptedException {
		int id = (int)(Math.random() * 1000) + 1;
		
		driver.get("http://localhost:8080/login");
		driver.manage().window().maximize();
		Thread.sleep(1000);
		driver.findElement(By.linkText("New a user?")).click();
		Thread.sleep(500);
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).sendKeys("New Freelancer " + id);
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("newfreelancer" + id + "@connect.com");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("password");
		driver.findElement(By.id("password_again")).sendKeys("password");
		driver.findElement(By.id("freelancer")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".btn")).click();
		driver.findElement(By.cssSelector(".flex-grow-1")).click();
		assertEquals(driver.findElement(By.cssSelector(".success")).getText(),
				"Account successfully created!. Please log in!");
		Thread.sleep(1500);
	}
}
