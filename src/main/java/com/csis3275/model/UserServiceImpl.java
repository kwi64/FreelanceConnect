package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfileRepo;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfileRepo.save(newUser);
	}
	
	/*public List<UserProfile> readUsers()	{
		return (List<UserProfile>)userProfileRepo.findAll();
	}*/
	
	public UserProfile getUserProfileInfo(Long id) {
		return (UserProfile) userProfileRepo.findById(id).orElse(new UserProfile());
	}
	
	public void deleteUser(Long id) {
		userProfileRepo.deleteById(id);
	}
	
	public void updateInfo(UserProfile updatedUser) {
		//updatedUser.setIsFreelancer(true);
		userProfileRepo.save(updatedUser);
	}
}
