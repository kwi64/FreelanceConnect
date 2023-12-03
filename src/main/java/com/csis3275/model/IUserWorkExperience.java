package com.csis3275.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserWorkExperience extends CrudRepository<UserWorkExperience, Long>{
	
	@Query("SELECT jawe FROM UserWorkExperience jawe WHERE userId = :userId")
	List<UserWorkExperience> findAllByUserId(Long userId);
}
