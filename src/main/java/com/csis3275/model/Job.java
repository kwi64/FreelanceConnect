package com.csis3275.model;



import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	private String title;
	private String employerTitle;
	private String location;
	private String description;
	private LocalDate validityStartDate;
	private LocalDate validityEndDate;
	private Long wage;
	public Long getId() {
		return ID;
	}
	public Job() {}
	
	public Job(Long jID,
			String jTitle, 
			String jEmployerTitle, 
			String  jLocation,
			String jDesc, 
			LocalDate jValidityStartDate,
			LocalDate jValidityEndDate,
			Long jWage)	{
		super();
		this.ID = jID;
		this.title = jTitle;
		this.employerTitle = jEmployerTitle;
		this.location = jLocation;
		this.validityStartDate = jValidityStartDate;
		this.validityEndDate = jValidityEndDate;
		this.description = jDesc;
		this.wage = jWage;
	}

	

	



	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getEmployerTitle() {
		return employerTitle;
	}

	public void setEmployerTitle(String employerTitle) {
		this.employerTitle = employerTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getWage() {
		return wage;
	}

	public void setWage(Long wage) {
		this.wage = wage;
	}
	public LocalDate getValidityStartDate() {
		return validityStartDate;
	}
	public void setValidityStartDate(LocalDate validityStartDate) {
		this.validityStartDate = validityStartDate;
	}
	public LocalDate getValidityEndDate() {
		return validityEndDate;
	}
	public void setValidityEndDate(LocalDate validityEndDate) {
		this.validityEndDate = validityEndDate;
	}
	
}
