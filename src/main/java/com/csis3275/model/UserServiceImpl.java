package com.csis3275.model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

	@Autowired
	private IUserProfile userProfileRepo;
	
	//@Autowired
	//private IUserLocation userInfoRepo;
	
	//@Autowired 
	//private IUserExperience userExperienceRepo;
	
	public UserProfile createProfile(UserProfile newUser) {
		return userProfileRepo.save(newUser);
	}
	
	public List<UserProfile> readUsers()	{
		return (List<UserProfile>)userProfileRepo.findAll();
	}
	
	public UserProfile getUserProfileInfo(Long id) {
		return userProfileRepo.findById(id).orElse(new UserProfile());
	}
	
	public void deleteUser(Long id) {
		userProfileRepo.deleteById(id);
	}
	
	public void updateInfo(UserProfile data) {
		//UserProfile userToUpdate = userProfileRepo.findById(data.getId()).orElse(new UserProfile());
		userProfileRepo.save(data);
	}
	
	/*public void updateInfo(UserProfile updateUser) {
		updateUser.getId();
		userProfileRepo.save(updateUser);
	}*/
	/*
   public void updateInfo(UserProfile data, Long id) {
        UserProfile updateUser = userProfileRepo.findById(id).get();
 
        if (Objects.nonNull(data.getName()) && !"".equalsIgnoreCase(data.getName())) {
            updateUser.setName(data.getName());
        }
        else {
        	updateUser.setName(null);
        }
        if (Objects.nonNull(data.getAddress1()) && !"".equalsIgnoreCase(data.getAddress1())) {
            updateUser.setAddress1(data.getAddress1());
        } else {
        	updateUser.setAddress1(null);
        }
        if (Objects.nonNull(data.getAddress2()) && !"".equalsIgnoreCase(data.getAddress2())) {
            updateUser.setAddress1(data.getAddress2());
        } else {
        	updateUser.setAddress2(null);
        }
        if (Objects.nonNull(data.getCity()) && !"".equalsIgnoreCase(data.getCity())) {
            updateUser.setCity(data.getCity());
        } else {
        	updateUser.setCity(null);
        }
        if (Objects.nonNull(data.getCompany()) && !"".equalsIgnoreCase(data.getCompany())) {
            updateUser.setCompany(data.getCompany());
        } else {
        	updateUser.setCompany(null);
        }
        if (Objects.nonNull(data.getCountry()) && !"".equalsIgnoreCase(data.getCountry())) {
            updateUser.setCountry(data.getCountry());
        } else {
        	updateUser.setCountry(null);
        }
        if (Objects.nonNull(data.getEmail()) && !"".equalsIgnoreCase(data.getEmail())) {
            updateUser.setEmail(data.getEmail());
        } else {
        	updateUser.setEmail(null);
        }
        if (Objects.nonNull(data.getPassword()) && !"".equalsIgnoreCase(data.getPassword())) {
            updateUser.setPassword(data.getPassword());
        } else {
        	updateUser.setPassword(null);
        }
        if (Objects.nonNull(data.getProvince()) && !"".equalsIgnoreCase(data.getProvince())) {
            updateUser.setProvince(data.getProvince());
        } else {
        	updateUser.setProvince(null);
        }
        if (Objects.nonNull(data.getTitle()) && !"".equalsIgnoreCase(data.getTitle())) {
            updateUser.setTitle(data.getTitle());
        } else {
        	updateUser.setTitle(null);
        }
        userProfileRepo.save(updateUser);
    }*/
	/*public void updateInfo (@PathVariable long id, @RequestBody UserProfile data) {
		UserProfile updateUser = userProfileRepo.findById(id).orElse(new UserProfile());
		updateUser.setName(data.getName());
		updateUser.setAddress1(data.getAddress1());
		updateUser.setAddress2(data.getAddress2());
		updateUser.setCity(data.getCity());
		updateUser.setCompany(data.getCompany());
		updateUser.setCountry(data.getCountry());
		updateUser.setEmail(data.getEmail());
		updateUser.setPassword(data.getPassword());
		updateUser.setProvince(data.getProvince());
		updateUser.setTitle(data.getTitle());
		userProfileRepo.save(updateUser);
	}*/
}
