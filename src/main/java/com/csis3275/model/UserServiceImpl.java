package com.csis3275.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfileRepo;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfileRepo.save(newUser);
	}
	
	public UserProfile getUserInfo(Long id) {
		return userProfileRepo.findById(id).orElse(new UserProfile());
	}
	
	public void updateUser(UserProfile updateUser) {
		userProfileRepo.save(updateUser);
	}
}
