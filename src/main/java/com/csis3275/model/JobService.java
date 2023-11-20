package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	
	
	public Job createJob(Job newJob)	{
		return jobRepository.save(newJob);
	}
	
	
	public List<Job> listJobs()	{
		return (List<Job>)jobRepository.findAll();
	}
}