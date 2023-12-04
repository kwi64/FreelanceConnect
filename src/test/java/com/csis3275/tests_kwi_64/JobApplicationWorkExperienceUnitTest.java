package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model.JobApplicationWorkExperience;

class JobApplicationWorkExperienceUnitTest {

	
	JobApplicationWorkExperience experience;
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@BeforeEach
	void setUp() {
		experience = new JobApplicationWorkExperience();
	}

	
	/**
	 * Tests for experience Job Title
	 */
	@Test
	void testExperienceTitle() {
		experience.setTitle("Software Engineer");
		assertEquals(experience.getTitle(), "Software Engineer");
	}
	
	/**
	 * Tests for experience obtained Company
	 */
	@Test
	void testExperienceCompany() {
		experience.setCompany("H2Compute");
		assertEquals(experience.getCompany(), "H2Compute");
	}
	
	
	/**
	 * Tests for experience obtained Company Location
	 */
	@Test
	void testExperienceCompanyLocation() {
		experience.setLocation("The Netherlands");
		assertEquals(experience.getLocation(), "The Netherlands");
	}
	
	/**
	 * Tests for experience obtained Start date
	 */
	@Test
	void testExperienceCompanyFromDate() {
		experience.setFromDate(LocalDate.of(2022, 01, 01));
		assertEquals(experience.getFromDate(), LocalDate.of(2022, 01, 01));
	}
	
	/**
	 * Tests for experience obtained End date
	 */
	@Test
	void testExperienceCompanyToDate() {
		experience.setToDate(LocalDate.of(2022, 12, 01));
		assertEquals(experience.getToDate(), LocalDate.of(2022, 12, 01));
	}
	
	/**
	 * Tests for freelancer if he is currently working
	 */
	@Test
	void testFreelancerCurrentlyWorking() {
		experience.setCurrentlyWorking(true);
		assertEquals(experience.isCurrentlyWorking(), true);
	}
}
