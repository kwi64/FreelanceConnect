package com.csis3275.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfile;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfile.save(newUser);
	}
	
}

