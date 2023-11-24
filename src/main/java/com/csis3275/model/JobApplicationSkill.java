package com.csis3275.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="JobApplicationSkill")
public class JobApplicationSkill {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String action;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "job_application_id")
    private JobApplication jobApplication;
	
	@Column(name = "job_application_id", insertable = false, updatable = false)
    private Long jobApplicationId;
	
	public JobApplicationSkill(String name) {
		this.setName(name);
	}
	
	public JobApplicationSkill() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}



	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public void setJobApplication(JobApplication jobApplication) {
		this.jobApplication = jobApplication;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Long getJobApplicationId() {
//		return jobApplicationId;
//	}
//
//	public void setJobApplicationId(Long jobApplicationId) {
//		this.jobApplicationId = jobApplicationId;
//	}
	
}
