package com.csis3275.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobApplicationWorkExperienceRepository extends CrudRepository<JobApplicationWorkExperience, Long>{
	
	@Query("SELECT jawe FROM JobApplicationWorkExperience jawe WHERE jobApplicationId = :jobApplicationId")
	List<JobApplicationWorkExperience> findAllByJobApplicationId(Long jobApplicationId);
}
