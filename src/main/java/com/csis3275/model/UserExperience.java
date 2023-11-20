package com.csis3275.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="experience")
public class UserExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long expId;
	private String title;
	private String company;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfHire;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfQuit;
	
	
	public UserExperience(String title, String company, LocalDate dateOfHire, LocalDate dateOfQuit) {
		super();
		this.title = title;
		this.company = company;
		this.dateOfHire = dateOfHire;
		this.dateOfQuit = dateOfQuit;
	}
	
	public UserExperience() {	
	}
	
	public Long getExpId() {
		return expId;
	}
	public void setExpId(Long expId) {
		this.expId = expId;
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
	
}
