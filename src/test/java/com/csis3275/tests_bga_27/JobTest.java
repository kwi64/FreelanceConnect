package com.csis3275.tests_bga_27;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.csis3275.model.Job;
import com.csis3275.model.JobRepository;
import com.csis3275.model.JobService;

@SpringBootTest
@AutoConfigureMockMvc
public class JobTest {

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private JobRepository jobRepository; 
	
	Job job;
	

	
	@BeforeEach
	void setUp() {
		//Making a new sample Job Posting and saving to database
		job = new Job(1L, "Software Engineer I", "EPASS Software", "New West, CA", "Lorem Ipsum", LocalDate.of(2023, 11, 23), LocalDate.of(2023, 12, 31), "12");
		jobRepository.save(job);
	}
	

	@Test
	//Testing if Job was saved correctly
	void testJobExistance() {
		assertEquals(true, jobRepository.existsById((long)1));
	}
	@Test
	//Testing job title
	void testJobTitle() {
		assertEquals(job.getTitle(), "Software Engineer I");
	}
	
	@Test
	//Testing employer title
	void testEmployerTitle() {
		assertEquals(job.getEmployerTitle(), "EPASS Software");
	}
	
	@Test
	//Testing location
	void testLocation() {
		assertEquals(job.getLocation(), "New West, CA");
	}
	
	@Test
	//Testing location
	void testDescription() {
		assertEquals(job.getDescription(), "Lorem Ipsum");
	}
	
	@Test
	//Testing startDate
	void testStartDate() {
		assertEquals(job.getValidityStartDate(), LocalDate.of(2023, 11, 23));
	}
	
	@Test
	//Testing endDate
	void testEndDate() {
		assertEquals(job.getValidityEndDate(), LocalDate.of(2023, 12, 31));
	}
	
	@Test
	//Testing wage
	void testWage() {
		assertEquals(job.getWage(), "12");
	}
	
	@Test
	//Testing addition of new job
	void testNewJob() {
		job = new Job(2L, "Software Engineer II", "EPASS Software", "New West, CA", "Lorem Ipsum", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 12, 31), "15");
		jobRepository.save(job);
		assertEquals(jobRepository.count(), 1);
	}
	
	@Test
	//Testing delete Job Posting
	void testDeleteJob() {
		jobRepository.deleteById((long)1);
		assertEquals(false, jobRepository.existsById((long)1));
	}
	
}
