package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private IUserRepository repository;
	@Autowired 
	private IUserWorkExperience userExpRepo;
	
	public User getUserInfo(Long id) {
		return (UserProfile) repository.findById(id).orElse(new UserProfile());
	}
	
	public void updateInfo(UserProfile user) {
		UserProfile updatedUser = (UserProfile) repository.findById(user.getId()).orElse(new UserProfile());
		
		if(user.getName() != null) {
			updatedUser.setName(user.getName());
		}
		if(user.getUsername() != null) {
			updatedUser.setUsername(user.getUsername());
		}
		if(user.getPassword() != null) {
			updatedUser.setPassword(user.getPassword());
		}
		repository.save(updatedUser);
	}
	
	public UserWorkExperience createUser(UserWorkExperience user) {
		user.setUsername(user.getUsername().trim());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		
		return userExpRepo.save(user);
	}
	
	public User createUser(User user) {
		user.setUsername(user.getUsername().trim());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		user.setEnabled(true);
		
		return repository.save(user);
	}
	
	public User findUserByUsername(String username) {
		List<User> users = (List<User>)repository.findAll();
		User user = null;
		for(User u : users) {
			if(u.getUsername().equals(username)) {
				user = u;
			}
	    }
		return user;
	}
}
