package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.csis3275.model.JobApplicationSkill;

class JobApplicationSkillUnitTest {

	JobApplicationSkill skill;
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	@BeforeEach
	void setUp() {
		skill = new JobApplicationSkill();
	}
	
	@Test
	void testSkillName() {
		skill.setName("Java");
		assertEquals(skill.getName(), "Java");
	}
}
