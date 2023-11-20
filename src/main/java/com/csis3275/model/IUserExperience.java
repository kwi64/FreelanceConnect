package com.csis3275.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserExperience extends CrudRepository<UserExperience, Long>{

}
