package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model.JobApplication;
import com.csis3275.model.JobApplicationSkill;
import com.csis3275.model.JobApplicationStatus;
import com.csis3275.model.JobApplicationWorkExperience;

/**
 * 
 */
class JobApplicationUnitTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	
	JobApplication application;
	
	@BeforeEach
	void setUp() {
		application = new JobApplication();
	}
	
	
	/**
	 * Tests for application personal details 'Display Name'
	 */
	@Test
	void testApplicationDisplayName() {
		application.setName("Freelancer");
		assertEquals(application.getName(), "Freelancer");
	}
	
	/**
	 * Tests for application personal details 'Address Line 2'
	 */
	@Test
	void testApplicationAddressLine1() {
		application.setAddressLine1("123");
		assertEquals(application.getAddressLine1(), "123");
	}
	
	/**
	 * Tests for application personal details 'Address Line 1'
	 */
	@Test
	void testApplicationAddressLine2() {
		application.setAddressLine2("10th Ave");
		assertEquals(application.getAddressLine2(), "10th Ave");
	}
	
	/**
	 * Tests for application personal details 'City'
	 */
	@Test
	void testApplicationCity() {
		application.setCity("Burnaby");
		assertEquals(application.getCity(), "Burnaby");
	}
	
	/**
	 * Tests for application personal details 'Province'
	 */
	@Test
	void testApplicationProvince() {
		application.setProvince("BC");
		assertEquals(application.getProvince(), "BC");
	}
	
	/**
	 * Tests for application personal details 'Country'
	 */
	@Test
	void testApplicationCountry() {
		application.setCountry("CA");
		assertEquals(application.getCountry(), "CA");
	}
	
	
	/**
	 * Tests for application work experience list size after setting one
	 */
	@Test
	void testApplicationWorkExperienceListSize() {
		
		List<JobApplicationWorkExperience> experiences = new ArrayList<JobApplicationWorkExperience>();
		experiences.add(new JobApplicationWorkExperience());
		
		application.setWorkExperienceList(experiences);
		assertEquals(application.getWorkExperienceList().size(), 1);
	}
	
	
	/**
	 * Tests for application skill list size after setting one
	 */
	@Test
	void testApplicationSkillListSize() {
		
		List<JobApplicationSkill> skills = new ArrayList<JobApplicationSkill>();
		skills.add(new JobApplicationSkill());
		
		application.setSkillList(skills);
		assertSame(application.getSkillList().size(), 1);
	}
	
	
	/**
	 * Tests for application resume file name
	 */
	@Test
	void testResumeFileName() {
		application.setResumeFileName("Resume.pdf");
		assertEquals(application.getResumeFileName(), "Resume.pdf");
	}
	
	
	/**
	 * Tests for application resume file path
	 */
	@Test
	void testResumeFilePath() {
		application.setResumePath("uploads/1/1/Resume.pdf");
		assertEquals(application.getResumePath(), "uploads/1/1/Resume.pdf");
	}
	
	/**
	 * Tests for application cover letter content
	 */
	@Test
	void testApplicationCoverLetter() {
		application.setCoverLetter("sample cover letter content");
		assertEquals(application.getCoverLetter(), "sample cover letter content");
	}
	
	/**
	 * Tests for application status changing to INPROGRESS
	 */
	@Test
	void testApplicationInProgress() {
		application.setApplicationStatus(JobApplicationStatus.INPROGRESS);
		assertEquals(application.getApplicationStatus(), JobApplicationStatus.INPROGRESS);
	}
	
	/**
	 * Tests for application status changing to SUBMITTED
	 */
	@Test
	void testApplicationSubmitted() {
		application.setApplicationStatus(JobApplicationStatus.SUBMITTED);
		assertEquals(application.getApplicationStatus(), JobApplicationStatus.SUBMITTED);
	}
	
	/**
	 * Tests for application status changing to ACCEPTED
	 */
	@Test
	void testApplicationAccepted() {
		application.setApplicationStatus(JobApplicationStatus.ACCEPTED);
		assertEquals(application.getApplicationStatus(), JobApplicationStatus.ACCEPTED);
	}
	
	/**
	 * Tests for application status changing to REJECTED
	 */
	@Test
	void testApplicationRejected() {
		application.setApplicationStatus(JobApplicationStatus.REJECTED);
		assertEquals(application.getApplicationStatus(), JobApplicationStatus.REJECTED);
	}
	

}
