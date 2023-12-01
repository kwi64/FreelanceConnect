package com.csis3275.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExperienceServiceImpl {
	
	@Autowired
	private IUserWorkExperience repository;
	
	public UserWorkExperience getUserExperience(Long id) {
		return (UserWorkExperience) repository.findById(id).orElse(new UserWorkExperience());
	}


	public void updateExperience(UserWorkExperience newInfo, Long id) {
		UserWorkExperience updatedUser = (UserWorkExperience) repository.findById(id).orElse(new UserWorkExperience());
		if(newInfo.getTitle() != null) {
		updatedUser.setTitle(newInfo.getTitle());
		}
		if(newInfo.getDateOfHire() != null) {
		updatedUser.setDateOfHire(newInfo.getDateOfHire());
		}
		if(newInfo.getDateOfQuit() != null) {
		updatedUser.setDateOfQuit(newInfo.getDateOfQuit());
		}
		if(newInfo.getCompany() != null) {
		updatedUser.setCompany(newInfo.getCompany());
		}
		repository.save(updatedUser);
	}
	
}
