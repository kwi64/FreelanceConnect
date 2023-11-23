package com.csis3275.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userDAO;

	@Override
	public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDAO.findUserByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}

//		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());

//		return org.springframework.security.core.userdetails.User
//                .withUsername(user.getUsername())
//                .password(user.getPassword())
//                .roles(authority.getAuthority())
//                .accountLocked(false)
//                .build();

		return new UserPrincipal(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getRole(), user.isEnabled());
	}
}
