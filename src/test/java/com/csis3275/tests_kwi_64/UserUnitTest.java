package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model.Role;
import com.csis3275.model.User;

class UserUnitTest {
	
	User freelancer;
	User employer;
	
	@BeforeEach
	void setUp() {
		freelancer = new User("Freelancer", "freelancer@connect.com", "passwordFreelancer", Role.FREELANCER, true);
		employer = new User("Employer", "employer@connect.com", "passwordEmployer", Role.EMPLOYER, true);
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	/**
	 * Tests for Freelancer Display name
	 */
	
	void testFreelancerDisplayName() {
		assertEquals(freelancer.getName(), "Freelancer");
	}
	
	/**
	 * Tests for Employer Display name
	 */
	@Test
	void testEmployerDisplayName() {
		assertEquals(employer.getName(), "Employer");
	}
	
	/**
	 * Tests for Freelancer Username
	 */
	@Test
	void testFreelancerUsername() {
		assertEquals(freelancer.getUsername(), "freelancer@connect.com");
	}
	
	
	/**
	 * Tests for Employer Username
	 */
	@Test
	void testEmployerUsername() {
		assertEquals(employer.getUsername(), "employer@connect.com");
	}
	
	
	/**
	 * Tests for Freelancer Password
	 */
	@Test
	void testFreelancerPassword() {
		assertEquals(freelancer.getPassword(), "passwordFreelancer");
	}
	
	
	/**
	 * Tests for Employer Password
	 */
	@Test
	void testEmployerPassword() {
		assertEquals(employer.getPassword(), "passwordEmployer");
	}
	
	
	/**
	 * Tests for Freelancer Role
	 */
	@Test
	void testFreelancerRole() {
		assertEquals(freelancer.getRole(), Role.FREELANCER);
	}

	
	/**
	 * Tests for Employer Role
	 */
	@Test
	void testEmployerRole() {
		assertEquals(employer.getRole(), Role.EMPLOYER);
	}
	
	
	/**
	 * Tests for Freelancer Enabled status
	 */
	@Test
	void testFreelancerEnabled() {
		assertEquals(freelancer.isEnabled(), true);
	}

	/**
	 * Tests for Employer Enabled status
	 */
	@Test
	void testEmployerEnabled() {
		assertEquals(employer.isEnabled(), true);
	}
}
