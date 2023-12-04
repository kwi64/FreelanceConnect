package com.csis3275.tests_kwi_64;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.endsWith;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}
	
	
	/**
	 * Tests for registration form
	 * @throws Exception
	 */
	@Test
	@Order(1)
	void TestRegistrationForm() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
				.get("/register")
				.accept(MediaType.TEXT_HTML)) //From springframework.http
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(MockMvcResultMatchers.view().name("register/register"));
	}
	
	
	/**
	 * Tests for Freelancer registration
	 * @throws Exception
	 */
	@Test
	@Order(2)
	public void testFreelancerRegistration() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/register")
				.param("name", "New Freelancer")
				.param("username", "newfreelancer@connect.com")
				.param("password", "password")
				.param("password_again", "password")
				.param("role", "FREELANCER")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.flash().attributeExists("success"))
		.andExpect(MockMvcResultMatchers.flash().attribute("success", "Account successfully created!. Please log in!"));
	}
	
	/**
	 * Tests for Employer registration
	 * @throws Exception
	 */
	@Test
	@Order(3)
	public void testEmployerRegistration() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/register")
				.param("name", "New Employer")
				.param("username", "newemployer@connect.com")
				.param("password", "password")
				.param("password_again", "password")
				.param("role", "EMPLOYER")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.flash().attributeExists("success"))
		.andExpect(MockMvcResultMatchers.flash().attribute("success", "Account successfully created!. Please log in!"))
		.andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
	}
	
	
	/**
	 * Tests for login form
	 * @throws Exception
	 */
	@Test
	@Order(4)
	void TestLoginForm() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
				.get("/login")
				.accept(MediaType.TEXT_HTML)) //From springframework.http
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("user"))
				.andExpect(MockMvcResultMatchers.view().name("login/login"));
	}
	
	
	/**
	 * Tests for Freelancer login
	 * @throws Exception
	 */
	@Test
	@Order(5)
	public void testFreelancerLogin() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/login")
				.param("username", "newfreelancer@connect.com")
				.param("password", "password")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	/**
	 * Tests for Freelancer logout
	 * @throws Exception
	 */
	@Test
	@Order(6)
	void TestFreelancerLogout() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
				.get("/logout")
				.accept(MediaType.TEXT_HTML)) //From springframework.http
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.endsWith("/login")));
	}
	
	/**
	 * Tests for Employer login
	 * @throws Exception
	 */
	@Test
	@Order(7)
	public void testEmployerLogin() throws Exception {
		mvc.perform( MockMvcRequestBuilders
				.post("/login")
				.param("username", "newemployer@connect.com")
				.param("password", "password")
				.contentType(MediaType.MULTIPART_FORM_DATA)
				.accept(MediaType.TEXT_HTML))
		.andDo(print())
		.andExpect(status().is3xxRedirection())
		.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}
	
	
	/**
	 * Tests for employer logout
	 * @throws Exception
	 */
	@Test
	@Order(8)
	void TestEmployerLogout() throws Exception	{
		mvc.perform(MockMvcRequestBuilders
				.get("/logout")
				.accept(MediaType.TEXT_HTML)) //From springframework.http
				.andDo(print())
				.andExpect(status().is3xxRedirection())
				.andExpect(MockMvcResultMatchers.header().string("Location", Matchers.endsWith("/login")));
	}
}
