package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationWorkExperienceService {
	@Autowired
	private IJobApplicationWorkExperienceRepository repository;
	
	public JobApplicationWorkExperience createExperience(JobApplication application, JobApplicationWorkExperience experience) {
		experience.setJobApplication(application);
		return repository.save(experience);
	}
	
	public JobApplicationWorkExperience updateExperience(JobApplication application, JobApplicationWorkExperience experience) {
		experience.setJobApplication(application);
		return repository.save(experience);
	}
	
	public void deleteExperience(JobApplication application, JobApplicationWorkExperience experience) {
		experience.setJobApplication(application);
		repository.delete(experience);
	}
	
	public List<JobApplicationWorkExperience> getAllByJobApplicationId(Long jobApplicationId) {
		return repository.findAllByJobApplicationId(jobApplicationId);
	}
}
