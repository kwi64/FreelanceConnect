package com.csis3275.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfileRepo;
	
	//@Autowired
	//private IUserLocation userInfoRepo;
	
	//@Autowired 
	//private IUserExperience userExperienceRepo;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfileRepo.save(newUser);
	}
	
	public UserProfile getUserProfileInfo(Long id) {
		return userProfileRepo.findById(id).orElse(new UserProfile());
	}
	
	public void deleteUser(Long id)
	{
		userProfileRepo.deleteById(id);
	}
	
	public void updateInfo (UserProfile updatedUser) {
		userProfileRepo.save(updatedUser);
	}
	
	/*
	public UserLocation addInfo (UserLocation newInfo) {
		return userInfoRepo.save(newInfo);
	}
	
	public UserLocation getUserLocationInfo(Long id) {
		return userInfoRepo.findById(id).orElse(new UserLocation());
	}
	
	public void deleteUserLocation(Long id)
	{
		userInfoRepo.deleteById(id);
	}
	
	
	/*public void updateUser(UserProfile updateUser) {
		userProfileRepo.save(updateUser);
	}*/
	
}
