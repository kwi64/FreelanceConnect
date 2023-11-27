package com.csis3275.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJobApplicationRepository extends CrudRepository<JobApplication, Long> {

	@Query("SELECT ja FROM JobApplication ja WHERE userId = :userId AND jobId = :jobId")
	Optional<JobApplication> findJobApplicationByUserIdAndJobId(Long userId, Long jobId);
	
	@Query("SELECT ja FROM JobApplication ja WHERE jobId = :ID")
	List<JobApplication> findAllApplicationsByJobId(Long ID);
}
