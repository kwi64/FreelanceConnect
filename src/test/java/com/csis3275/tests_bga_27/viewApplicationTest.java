package com.csis3275.tests_bga_27;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class viewApplicationTest {
	
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
	
	//This test creates a job, then a job application for that job then checks for the job application existence in view Applications
	//on the employers side.
	 @Test
	  public void viewApplication() {
	    driver.get("http://localhost:8080/login");
	    driver.manage().window().setSize(new Dimension(1295, 695));
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("employerTest@connect.com");
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
	    driver.findElement(By.id("validityEndDate")).sendKeys("2023-12-10");
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).sendKeys("Job Description");
	    driver.findElement(By.cssSelector(".overflow-auto")).click();
	    driver.findElement(By.cssSelector("p:nth-child(18) > input")).click();
	    driver.findElement(By.cssSelector("p > input")).click();
	    driver.findElement(By.cssSelector(".btn-outline-warning")).click();
	    driver.findElement(By.linkText("Logout")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("freelancerTest@connect.com");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("password");
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector(".d-flex:nth-child(1) > .m-0:nth-child(3)")).click();
	    driver.findElement(By.linkText("Apply Now")).click();
	    driver.findElement(By.id("addressLine1")).click();
	    driver.findElement(By.id("addressLine1")).sendKeys("Address test");
	    driver.findElement(By.id("addressLine2")).click();
	    driver.findElement(By.id("addressLine2")).sendKeys("Address test2");
	    driver.findElement(By.id("city")).click();
	    driver.findElement(By.id("city")).sendKeys("Surrey");
	    driver.findElement(By.id("province")).click();
	    driver.findElement(By.id("province")).sendKeys("BC");
	    driver.findElement(By.id("country")).click();
	    driver.findElement(By.id("country")).sendKeys("Canada");
	    driver.findElement(By.cssSelector(".btn-success")).click();
	    driver.findElement(By.cssSelector(".btn-outline-warning")).click();
	    driver.findElement(By.cssSelector(".border-start")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).sendKeys("employer@connect.com");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).sendKeys("password");
	    driver.findElement(By.cssSelector(".btn")).click();
	    driver.findElement(By.cssSelector(".nav-item:nth-child(1) > .nav-link")).click();
	    driver.findElement(By.cssSelector("tr:nth-child(5) .btn")).click();
	    
	    assertEquals(driver.findElement(By.cssSelector("div.row:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1)")).getText(),"Freelancer");
	    assertEquals(driver.findElement(By.cssSelector("div.row:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2)")).getText(),"Canada");
	  }

}
