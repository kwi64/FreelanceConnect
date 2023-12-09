package com.csis3275.tests_bga_27;

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

public class createJobTest {
	
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
	

	 @Test
	  public void createJob() {
	    driver.get("http://localhost:8080/login");
	    driver.manage().window().setSize(new Dimension(1295, 695));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("employer@connect.com");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("password");
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector(".nav-item:nth-child(1) > .nav-link")).click();
	    driver.findElement(By.linkText("Create New Posting")).click();
	    driver.findElement(By.id("title")).click();
	    driver.findElement(By.id("title")).sendKeys("Job Title");
	    driver.findElement(By.id("employerTitle")).click();
	    driver.findElement(By.id("employerTitle")).sendKeys("Employer Name");
	    driver.findElement(By.id("location")).click();
	    driver.findElement(By.id("location")).sendKeys("Canada");
	    driver.findElement(By.cssSelector("form")).click();
	    driver.findElement(By.id("wage")).click();
	    driver.findElement(By.id("wage")).sendKeys("22");
	    driver.findElement(By.id("validityStartDate")).click();
	    driver.findElement(By.id("validityStartDate")).sendKeys("2023-12-03");
	    driver.findElement(By.id("validityEndDate")).click();
	    driver.findElement(By.id("validityEndDate")).sendKeys("2023-12-16");
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).sendKeys("Job Description");
	    driver.findElement(By.cssSelector("p:nth-child(18) > input")).click();
	    
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > h3:nth-child(2)")).getText(),"Job Title");
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > p:nth-child(3)")).getText(),"Employer Name");
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > p:nth-child(4)")).getText(),"Canada");
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > p:nth-child(5)")).getText(),"22");
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > p:nth-child(6)")).getText(),"2023-12-03 - 2023-12-16");
	    assertEquals(driver.findElement(By.cssSelector("div.container-fluid:nth-child(2) > p:nth-child(7)")).getText(),"Job Description");
	 }
	 
	 
}
