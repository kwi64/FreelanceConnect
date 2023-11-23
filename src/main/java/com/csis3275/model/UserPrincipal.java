package com.csis3275.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {
	private String username;
	private String password;
	private long id;
	private String displayName;
	private Role role;
	private boolean enabled;

	public UserPrincipal(long id, String displayName, String username, String password, Role role, boolean enabled) {
		this.id = id;
		this.displayName = displayName;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enabled = enabled;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public boolean isEmployer() {
		return this.role == Role.EMPLOYER;
	}
	
	public boolean isFreelancer() {
		return this.role == Role.FREELANCER;
	}
}
