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
public class UserWorkExperience{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_experience_id")
	private User user;
	
	@Column(name = "user_experience_id", insertable = false, updatable = false)
    private Long userId;
	
	private String title;
	private String company;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfHire;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfQuit;
	private boolean currentlyWorking;


	
	public UserWorkExperience(User user, String title, String company, LocalDate dateOfHire, LocalDate dateOfQuit, boolean currentlyWorking) {
	this.user = user;
	this.title = title;
	this.company = company;
	this.dateOfHire = dateOfHire;
	this.dateOfQuit = dateOfQuit;
	this.currentlyWorking = currentlyWorking;
	}
	public UserWorkExperience() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public boolean isCurrentlyWorking() {
		return currentlyWorking;
	}
	public void setCurrentlyWorking(boolean currentlyWorking) {
		this.currentlyWorking = currentlyWorking;
	}
	
}
