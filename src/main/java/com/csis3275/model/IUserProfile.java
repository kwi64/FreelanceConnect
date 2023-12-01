package com.csis3275.model;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public interface IUserProfile extends IUserRepository{
}