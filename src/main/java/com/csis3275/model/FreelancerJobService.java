package com.csis3275.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class FreelancerJobService {
	@Autowired
	private JobRepository repository;
	
	public List<Job> getJobPostings() {
		return repository.findAllOrderByStartDate();
	}
	
	public Job getJobPosting(long id) {
		return repository.findById(id).orElse(null);
	}
}
