package com.csis3275.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csis3275.model.Job;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private IJobApplicationRepository repository;
	
	
	
	public Job createJob(Job newJob)	{
		return jobRepository.save(newJob);
	}
	
	
	public List<Job> listJobs()	{
		return (List<Job>)jobRepository.findAll();
	}

	public Job viewApplications(Long ID) {
		return jobRepository.findById(ID).orElse(new Job());
	}
	
	public List<JobApplication> listJobApplications(Long ID) {
		List<JobApplication> jobList = (List<JobApplication>)repository.findAllApplicationsByJobId(ID);
		return jobList;
	}


}