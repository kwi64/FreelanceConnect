package com.csis3275.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserWorkExperience")
public class UserWorkExperience extends User{
	
	private String title;
	private String company;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfHire;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfQuit;
	
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//    private User userExperience;
//	
//	@Column(name = "user_id", insertable = false, updatable = false)
//    private Long userId;

	public UserWorkExperience(String name, String username, String password, Role role, boolean enabled,
		String title, String company, LocalDate dateOfHire, LocalDate dateOfQuit) {
	super(name, username, password, role, enabled);
	this.title = title;
	this.company = company;
	this.dateOfHire = dateOfHire;
	this.dateOfQuit = dateOfQuit;
	}
	public UserWorkExperience() {
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public LocalDate getDateOfHire() {
		return dateOfHire;
	}
	public void setDateOfHire(LocalDate dateOfHire) {
		this.dateOfHire = dateOfHire;
	}
	public LocalDate getDateOfQuit() {
		return dateOfQuit;
	}
	public void setDateOfQuit(LocalDate dateOfQuit) {
		this.dateOfQuit = dateOfQuit;
	}
//	public User getUserExperience() {
//		return userExperience;
//	}
//	public void setUserExperience(User userExperience) {
//		this.userExperience = userExperience;
//	}

	
	
	
}
