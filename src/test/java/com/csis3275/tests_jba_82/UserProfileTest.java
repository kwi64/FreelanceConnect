package com.csis3275.tests_jba_82;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.csis3275.model.IUserRepository;
import com.csis3275.model.Role;
import com.csis3275.model.User;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;
import com.csis3275.model.UserServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileTest {

	
	private User user;
	private User user2;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IUserRepository userProfileRepository; 
	
	@BeforeEach
	void setUp() {
		//Creating a new user and saving it in the database, 
		Role roleFreelancer = Role.FREELANCER;
		user = userService.createUser(new UserProfile(
				"test", "test@gmail.com", "password", 
				roleFreelancer, true,null,null,null,null,null,null
				));
		userProfileRepository.save(user);
		
		user2 = userService.createUser(new UserProfile(
				"test2", "test2@gmail.com", "password2", 
				roleFreelancer, true,null,null,null,null,null,null
				));
		userProfileRepository.save(user2);
	}
	
	@Test
	//Having a number at the start of the method causes JUnit to load them in alphabetical order
	void testAUserId(){
		//Check if first user was created successfully 
		assertEquals(true, userProfileRepository.existsById((long)1));
	} 

	@Test 
	void testBUserName() {
		//tests if the users name in the database is equal to the 'user' objects name
		//both of the following work for testing 
		
		assertEquals("test", user.getName());
		
		//User testUser = userProfileRepository.findById((long)1).get();
		//assertEquals(testUser.getName(), user.getName());
	}
	
	@Test
	void testCDeleteUser() {
		//Check if first user was deleted successfully
		userProfileRepository.deleteById((long)1);
		assertEquals(false, userProfileRepository.existsById((long)1));
	}
//	@Test
//	void testDUpdateUser() {
//		//test if updating user works
//		UserProfile newUser = new UserProfile();
//		newUser.setName("Jack");
//		newUser.setUsername("Jack@gmail.com");
//		
//		//Check before update
//		assertEquals("test2", user2.getName());
//		
//		userService2.updateInfo(newUser, (long)2);
//		
//		//Check after update
//		assertEquals("Jack", newUser.getName());
//	}
}