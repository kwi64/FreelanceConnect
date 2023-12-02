package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserExperienceServiceImpl {
	
	@Autowired
	private IUserWorkExperience repository;
	
	public UserWorkExperience createUserExperience(User user, UserWorkExperience experience) {
		return repository.save(experience);
	}
	
	public UserWorkExperience updateUserExperience(User user, UserWorkExperience experience) {
		experience.setUser(user);
		return repository.save(experience);
	}
	
	public void deleteUserExperience(User user, UserWorkExperience experience) {
		experience.setUser(user);
		repository.delete(experience);
	}
	
	public List<UserWorkExperience> getAllByUserId(Long userId) {
		return repository.findAllByUserId(userId);
	}

	public UserWorkExperience getUserExperience(long id) {
		return (UserWorkExperience) repository.findById(id).orElse(new UserWorkExperience());
	}
	
}
