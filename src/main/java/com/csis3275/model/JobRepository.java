package com.csis3275.model;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends CrudRepository<Job, Long>	{
	
	@Query("SELECT j FROM Job j ORDER BY j.validityStartDate DESC")
	List<Job> findAllOrderByStartDate();
}