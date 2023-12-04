package com.csis3275.tests_kwi_64;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import com.csis3275.model.Role;
import com.csis3275.model.UserPrincipal;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FreelancerJobControllerTest {
	
	@Autowired
	private MockMvc mvc;

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	
	@BeforeEach
	void setUp(){
        String password = "password";

        UserPrincipal userPrincipal = new UserPrincipal(1, "Freelancer", "freelancer@connect.com", "password", Role.FREELANCER, true);
        
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userPrincipal, password, userPrincipal.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@AfterEach
    public void clear() {
        SecurityContextHolder.clearContext();
    }

	/**
	 * Tests for Freelancer jobs page
	 * @throws Exception
	 */
	@Test
	@Order(1)
	void TestJobsPage() throws Exception	{
		
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/jobs")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("view"))
				.andExpect(model().attribute("view", "freelancer/jobs/jobs"))
				.andExpect(model().attributeExists("jobs"))
				.andExpect(MockMvcResultMatchers.view().name("layout"));
	}
	
	/**
	 * Tests for Freelancer jobs selected page: here selects 1 that comes from a job pre-seeded on app start
	 * @throws Exception
	 */
	@Test
	@Order(2)
	void TestJobSelected() throws Exception	{
		
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/jobs/1")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("view"))
				.andExpect(model().attribute("view", "freelancer/jobs/jobs"))
				.andExpect(model().attributeExists("jobs"))
				.andExpect(model().attributeExists("selected"))
				.andExpect(model().attributeExists("canApply"))
				.andExpect(model().attribute("canApply", Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.view().name("layout"));
	}
	
	/**
	 * Tests for freelancer jobs apply page (for an existing job seeded at startup)
	 * @throws Exception
	 */
	@Test
	@Order(3)
	void TestJobsApplyPage() throws Exception	{
		
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/jobs/1/apply")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("view"))
				.andExpect(model().attribute("view", "freelancer/apply/apply"))
				.andExpect(model().attributeExists("job_id"))
				.andExpect(model().attribute("job_id", Matchers.is(1L)))
				.andExpect(model().attributeExists("application"))
				.andExpect(model().attributeExists("workExperienceList"))
				.andExpect(model().attributeExists("skillList"))
				.andExpect(model().attributeExists("canApply"))
				.andExpect(model().attribute("canApply", Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.view().name("layout"));
	}
	
	/**
	 * Tests for saving freelancer job application step 1
	 * @throws Exception
	 */
	@Test
	@Order(4)
	void TestJobsApplyStep1() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
			.post("/freelancer/jobs/1/apply")
			.param("id", "1")
			.param("userId", "1")
			.param("jobId", "1")
			.param("applicationStatus", "")
			.param("name", "Freelancer Full Name")
			.param("addressLine1", "addrL1")
			.param("addressLine2", "addrL2")
			.param("city", "New West")
			.param("province", "BC")
			.param("country", "CA")
			.param("workExperienceList[0].id", "1")
			.param("workExperienceList[0].title", "Pos1")
			.param("workExperienceList[0].company", "Comp1")
			.param("workExperienceList[0].location", "NewWest,BC,CA")
			.param("workExperienceList[0].fromDate", "2020-01-08")
			.param("workExperienceList[0].toDate", "2021-01-08")
			.param("workExperienceList[1].id", "2")
			.param("workExperienceList[1].title", "Pos2")
			.param("workExperienceList[1].company", "Comp2")
			.param("workExperienceList[1].location", "NewWest,BC,CA")
			.param("workExperienceList[1].fromDate", "2021-01-08")
			.param("workExperienceList[1].toDate", "2022-01-08")
			.param("skillList[0].id", "1")
			.param("skillList[0].name", "Java")
			.param("skillList[1].id", "2")
			.param("skillList[1].name", "c#")
			.param("skillList[2].id", "3")
			.param("skillList[2].name", "JavaScript")
			.param("action", "save")
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.accept(MediaType.TEXT_HTML))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.flash().attributeExists("success"))
			.andExpect(MockMvcResultMatchers.flash().attribute("success", "Successfully Saved!"))
			.andExpect(MockMvcResultMatchers.redirectedUrl("/freelancer/jobs/1/apply"));
	}
	
	
	/**
	 * Tests for saving freelancer job application and going to step2 ( by clicking Next : action = "next" )
	 * @throws Exception
	 */
	@Test
	@Order(5)
	void TestJobsApplyStep1Next() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
			.post("/freelancer/jobs/1/apply")
			.param("id", "1")
			.param("userId", "1")
			.param("jobId", "1")
			.param("applicationStatus", "")
			.param("name", "Freelancer Full Name")
			.param("addressLine1", "addrL1")
			.param("addressLine2", "addrL2")
			.param("city", "New West")
			.param("province", "BC")
			.param("country", "CA")
			.param("workExperienceList[0].id", "1")
			.param("workExperienceList[0].title", "Pos1")
			.param("workExperienceList[0].company", "Comp1")
			.param("workExperienceList[0].location", "NewWest,BC,CA")
			.param("workExperienceList[0].fromDate", "2020-01-08")
			.param("workExperienceList[0].toDate", "2021-01-08")
			.param("workExperienceList[1].id", "2")
			.param("workExperienceList[1].title", "Pos2")
			.param("workExperienceList[1].company", "Comp2")
			.param("workExperienceList[1].location", "NewWest,BC,CA")
			.param("workExperienceList[1].fromDate", "2021-01-08")
			.param("workExperienceList[1].toDate", "2022-01-08")
			.param("skillList[0].id", "1")
			.param("skillList[0].name", "Java")
			.param("skillList[1].id", "2")
			.param("skillList[1].name", "c#")
			.param("skillList[2].id", "3")
			.param("skillList[2].name", "JavaScript")
			.param("action", "next")
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.accept(MediaType.TEXT_HTML))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/freelancer/jobs/1/upload"));
	}
	
	
	/**
	 * Tests for freelancer jobs upload page (for an existing job seeded at startup)
	 * @throws Exception
	 */
	@Test
	@Order(6)
	void TestJobsApplyUploadPage() throws Exception	{
		
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/jobs/1/upload")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("view"))
				.andExpect(model().attribute("view", "freelancer/upload/upload"))
				.andExpect(model().attributeExists("job_id"))
				.andExpect(model().attribute("job_id", Matchers.is(1L)))
				.andExpect(model().attributeExists("application"))
				.andExpect(model().attribute("canApply", Matchers.is(true)))
				.andExpect(MockMvcResultMatchers.view().name("layout"));
	}
	
	
	/**
	 * Tests for saving Freelancer upload step, where the Freelancer uploads resume document and the cover letter text
	 * @throws Exception
	 */
	@Test
	@Order(7)
	void TestJobsApplyUpload() throws Exception	{
		MockMultipartFile file = new MockMultipartFile(
                "file",
                "sample.pdf",
                MediaType.APPLICATION_PDF_VALUE,
                getClass().getResourceAsStream("/files/sample.pdf")
        );
		
		mvc.perform(MockMvcRequestBuilders.multipart("/freelancer/jobs/1/upload")
            .file(file)
			.param("coverLetter", "sample cover letter content")
			.accept(MediaType.TEXT_HTML))
			.andDo(print())
			.andExpect(status().is3xxRedirection())
			.andExpect(MockMvcResultMatchers.redirectedUrl("/freelancer/jobs/1"));
	}
	
	
	/**
	 * Tests for Freelancer selecting the job posting just applied. 'canApply' should be false, which prevents freelancer from applying to the same job again
	 * @throws Exception
	 */
	@Test
	@Order(8)
	void TestJobApplied() throws Exception	{
		
		mvc.perform(MockMvcRequestBuilders
				.get("/freelancer/jobs/1")
				.accept(MediaType.TEXT_HTML))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("view"))
				.andExpect(model().attribute("view", "freelancer/jobs/jobs"))
				.andExpect(model().attributeExists("jobs"))
				.andExpect(model().attributeExists("selected"))
				.andExpect(model().attributeExists("canApply"))
				.andExpect(model().attribute("canApply", Matchers.is(false)))
				.andExpect(MockMvcResultMatchers.view().name("layout"));
	}
}
 