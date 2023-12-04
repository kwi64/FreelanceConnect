package com.csis3275.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfileRepo;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfileRepo.save(newUser);
	}
	
	public UserProfile getUserProfileInfo(Long id) {
		return (UserProfile) userProfileRepo.findById(id).orElse(new UserProfile());
	}
	
	public void deleteUser(Long id) {
		userProfileRepo.deleteById(id);
	}
	
	public void updateInfo(UserProfile user, Long id) {
		UserProfile updatedUser = (UserProfile) userProfileRepo.findById(id).orElse(new UserProfile());
		if(user.getAddress1() != null) {
			updatedUser.setAddress1(user.getAddress1());
		}
		if(user.getAddress2() != null) {
			updatedUser.setAddress2(user.getAddress2());
		}
		if(user.getCity() != null) {
			updatedUser.setCity(user.getCity());
		}
		if(user.getCountry() != null) {
			updatedUser.setCountry(user.getCountry());
		}
		if(user.getProvince() != null) {
			updatedUser.setProvince(user.getProvince());
		}
		if(user.getSkills() != null) {
			updatedUser.setSkills(user.getSkills());
		}
		//from User class
		if(user.getName() != null) {
			updatedUser.setName(user.getName());
		}
		if(user.getUsername() != null) {
			updatedUser.setUsername(user.getUsername());
		}
		if(user.getPassword() != null) {
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			updatedUser.setPassword(user.getPassword());
		}
		updatedUser.setRole(updatedUser.getRole());
		updatedUser.setEnabled(updatedUser.isEnabled());
		userProfileRepo.save(updatedUser);
	}
}
