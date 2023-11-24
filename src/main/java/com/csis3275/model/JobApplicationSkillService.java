package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationSkillService {
	@Autowired
	private IJobApplicationSkillRepository repository;
	
	public JobApplicationSkill createSkill(JobApplication application, JobApplicationSkill skill) {
		skill.setJobApplication(application);
		return repository.save(skill);
	}
	
	
	public JobApplicationSkill updateSkill(JobApplication application, JobApplicationSkill skill) {
		skill.setJobApplication(application);
		return repository.save(skill);
	}
	
	public void deleteSkill(JobApplication application, JobApplicationSkill skill) {
		skill.setJobApplication(application);
		repository.delete(skill);
	}
	
	public List<JobApplicationSkill> getAllByJobApplicationId(Long jobApplicationId) {
		return repository.findAllByJobApplicationId(jobApplicationId);
	}
}
