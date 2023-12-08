package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class EmployerLoginLogoutBlackboxTest {
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
	public void employerLoginLogout() throws InterruptedException {
		driver.get("http://localhost:8080/login");
		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("employer@connect.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn")).click();
		assertEquals(driver.findElement(By.cssSelector(".navbar-text")).getText(),
				"Employer");
		Thread.sleep(1500);
	    driver.findElement(By.cssSelector(".fa-user")).click();
	    Thread.sleep(1500);
	    driver.findElement(By.linkText("Logout")).click();
	    Thread.sleep(1500);
	}
}
