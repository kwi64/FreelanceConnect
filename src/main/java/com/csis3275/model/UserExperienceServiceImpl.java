package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExperienceServiceImpl {
	
	@Autowired
	private IUserWorkExperience repository;
	
	public UserWorkExperience createUserExperience(UserWorkExperience experience) {
		return repository.save(experience);
	}
	
	public UserWorkExperience updateUserExperience(UserWorkExperience experience) {
		return repository.save(experience);
	}
	
	public void deleteUserExperiences(List <UserWorkExperience> experienceList) {
		repository.deleteAll(experienceList);
	}
	
	public List<UserWorkExperience> getAllByUserId(Long userId) {
		return repository.findAllByUserId(userId);
	}

	public UserWorkExperience getUserExperience(long id) {
		return (UserWorkExperience) repository.findById(id).orElse(new UserWorkExperience());
	}
	
}
