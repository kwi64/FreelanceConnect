package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
	@Autowired
	private IUserRepository repository;
	
	
	public User createUser(User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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
