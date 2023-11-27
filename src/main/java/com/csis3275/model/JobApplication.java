package com.csis3275.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "JobApplication")
public class JobApplication {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private long userId;
	private long jobId;

	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String province;
	private String country;
	private String resumeFileName;
	private String resumePath;
	private String coverLetter;
	private JobApplicationStatus applicationStatus;
	private String action;
	
	@OneToMany(mappedBy = "jobApplication", cascade = CascadeType.REMOVE)
    private List<JobApplicationWorkExperience> workExperienceList;
	
	@OneToMany(mappedBy = "jobApplication", cascade = CascadeType.REMOVE)
    private List<JobApplicationSkill> skillList;
	
	public JobApplication(long userId, long jobId, String name, String addressLine1, String addressLine2, String city, String province, String country, JobApplicationStatus applicationStatus) {
		this.userId = userId;
		this.jobId = jobId;
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.applicationStatus = applicationStatus;
	}
	
	public JobApplication() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(long jobId) {
		this.jobId = jobId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<JobApplicationWorkExperience> getWorkExperienceList() {
		return workExperienceList;
	}

	public void setWorkExperienceList(List<JobApplicationWorkExperience> workExperienceList) {
		this.workExperienceList = workExperienceList;
	}

	public List<JobApplicationSkill> getSkillList() {
		return skillList;
	}

	public void setSkillList(List<JobApplicationSkill> skillList) {
		this.skillList = skillList;
	}

	public JobApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(JobApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getResumeFileName() {
		return resumeFileName;
	}

	public void setResumeFileName(String resumeFileName) {
		this.resumeFileName = resumeFileName;
	}
	
}
