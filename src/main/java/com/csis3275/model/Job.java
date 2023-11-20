package com.csis3275.model;



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
	private String EmployerTitle;
	private String location;
	private String description;
	private String validityStartDate;
	private String validityEndDate;
	private Long wage;
	
	public Job(Long jID,
			String jTitle, 
			String jEmployerTitle, 
			String  jLocation,
			String jDesc, 
			String jValidityStartDate,
			String jValidityEndDate,
			Long jWage)	{
		
		this.ID = jID;
		this.title = jTitle;
		this.EmployerTitle = jEmployerTitle;
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
		return EmployerTitle;
	}

	public void setEmployerTitle(String employerTitle) {
		EmployerTitle = employerTitle;
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
	public String getValidityStartDate() {
		return validityStartDate;
	}
	public void setValidityStartDate(String validityStartDate) {
		this.validityStartDate = validityStartDate;
	}
	public String getValidityEndDate() {
		return validityEndDate;
	}
	public void setValidityEndDate(String validityEndDate) {
		this.validityEndDate = validityEndDate;
	}
}
