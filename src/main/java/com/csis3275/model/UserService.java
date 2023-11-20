package com.csis3275.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	private UserDAO userDAO;

	public UserService(UserDAO dao) {
		this.userDAO = dao;
	}

	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findUserByUsername(username);

		if (user == null) {
			new UsernameNotFoundException("User not found with email: " + username);
		}

//		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

//		return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(authority.getAuthority())
//                .accountLocked(false)
//                .build();

		return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getRole());
	}

}
