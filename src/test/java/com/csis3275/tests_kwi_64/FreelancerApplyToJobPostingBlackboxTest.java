package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public class FreelancerApplyToJobPostingBlackboxTest {
	private static FirefoxDriver driver;
	private static JavascriptExecutor js;

	@BeforeAll
	public static void setUp() {
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("-private");
		driver = new FirefoxDriver(options);
		js = (JavascriptExecutor) driver;
	}

	@AfterAll
	public static void tearDown() {
		driver.quit();
	}

	@Test
	public void freelancerApplyToJobPosting() throws InterruptedException {
		driver.get("http://localhost:8080/login");
		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).sendKeys("freelancer@connect.com");
		driver.findElement(By.id("password")).click();
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".btn")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".d-flex:nth-child(3) > .m-0:nth-child(3)")).click();
		Thread.sleep(1500);
		driver.findElement(By.linkText("Apply Now")).click();
		driver.findElement(By.id("addressLine1")).click();
		driver.findElement(By.id("addressLine1")).sendKeys("123");
		driver.findElement(By.id("addressLine2")).click();
		driver.findElement(By.id("addressLine2")).sendKeys("12th Ave");
		driver.findElement(By.id("city")).click();
		driver.findElement(By.id("city")).sendKeys("Burnaby");
		driver.findElement(By.id("province")).click();
		driver.findElement(By.id("province")).sendKeys("BC");
		driver.findElement(By.id("country")).click();
		driver.findElement(By.id("country")).sendKeys("CA");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".col-12:nth-child(7)")).click();
		driver.findElement(By.id("newExperience")).click();
		driver.findElement(By.id("workExperienceList[0].title")).click();
		driver.findElement(By.id("workExperienceList[0].title")).sendKeys("Software Developer");
		driver.findElement(By.id("workExperienceList[0].company")).click();
		driver.findElement(By.id("workExperienceList[0].company")).sendKeys("H2");
		driver.findElement(By.id("workExperienceList[0].location")).click();
		driver.findElement(By.id("workExperienceList[0].location")).sendKeys("Vancouver, BC, CA");
		driver.findElement(By.id("\'workExperienceList[\'+ ${stat.index} + \'].fromDate\'")).click();
		driver.findElement(By.id("\'workExperienceList[\'+ ${stat.index} + \'].fromDate\'")).sendKeys("2022-01-01");
		driver.findElement(By.id("\'workExperienceList[\'+ ${stat.index} + \'].toDate\'")).click();
		driver.findElement(By.id("\'workExperienceList[\'+ ${stat.index} + \'].toDate\'")).sendKeys("2022-12-31");
		driver.findElement(By.cssSelector(".d-flex:nth-child(6)")).click();
		driver.findElement(By.cssSelector("#newSkill > .fa-solid")).click();
		Thread.sleep(1500);
		driver.findElement(By.name("skillList[0].name")).click();
		driver.findElement(By.name("skillList[0].name")).sendKeys("Java");
		driver.findElement(By.cssSelector("#newSkill > .fa-solid")).click();
		driver.findElement(By.name("skillList[1].name")).click();
		driver.findElement(By.name("skillList[1].name")).click();
		driver.findElement(By.name("skillList[1].name")).sendKeys("C#");
		driver.findElement(By.cssSelector("#newSkill > .fa-solid")).click();
		driver.findElement(By.name("skillList[2].name")).click();
		driver.findElement(By.name("skillList[2].name")).sendKeys("PHP");
		driver.findElement(By.cssSelector(".col-12:nth-child(8)")).click();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".btn-success")).click();
		driver.findElement(By.cssSelector(".sticky-bottom")).click();
		assertEquals(driver.findElement(By.cssSelector(".align-self-start")).getText(),
				"Successfully Saved!");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".btn-warning:nth-child(2)")).click();
		
		Thread.sleep(1500);
		WebElement fileInput = driver.findElement(By.cssSelector("input[type=file]"));
	    fileInput.sendKeys(Paths.get(Paths.get("").toString(), "src", "main", "resources", "static", "files", "sample.pdf").toAbsolutePath().toString());
		
		driver.switchTo().frame(0);

		driver.findElement(By.cssSelector("html")).click();
		{
			WebElement element = driver.findElement(By.id("tinymce"));
			js.executeScript(
					"if(arguments[0].contentEditable === 'true') {arguments[0].innerText = 'Sample Cover Letter Content'}",
					element);
		}
		
		driver.switchTo().defaultContent();
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(".row:nth-child(2)")).click();
		driver.findElement(By.name("action")).click();
		
		assertEquals(driver.findElement(By.linkText("You have applied!")).getText(),
				"You have applied!");
		Thread.sleep(1500);
	}
}
