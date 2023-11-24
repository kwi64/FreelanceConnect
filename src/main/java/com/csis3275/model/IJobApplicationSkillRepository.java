package com.csis3275.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobApplicationSkillRepository extends CrudRepository<JobApplicationSkill, Long>{

	@Query("SELECT jawe FROM JobApplicationSkill jawe WHERE jobApplicationId = :jobApplicationId")
	List<JobApplicationSkill> findAllByJobApplicationId(Long jobApplicationId);
}
