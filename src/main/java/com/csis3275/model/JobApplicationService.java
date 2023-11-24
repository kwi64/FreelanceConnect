package com.csis3275.model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {
	@Autowired
	private IJobApplicationRepository repository;
	
	public JobApplication createJobApplication(JobApplication jobApplication) {
		return repository.save(jobApplication);
	}
	
	public JobApplication updateJobApplication(JobApplication jobApplication) {
		return repository.save(jobApplication);
	}
	
	public JobApplication getJobApplication(Long id) {
		return repository.findById(id).orElse(new JobApplication());
	}
	
	public JobApplication getJobApplicationByUserIdAndJobId(long userId, long jobId) {
		JobApplication ja = repository.findJobApplicationByUserIdAndJobId(userId, jobId)
				.orElse(new JobApplication());
		
		if(ja.getId() == 0) {
			ja.setUserId(userId);
			ja.setJobId(jobId);
		}
		
		return ja;
	}
}
