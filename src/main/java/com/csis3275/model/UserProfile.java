package com.csis3275.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="userInfo")
public class UserProfile extends User{
	
//	private Long userId;
	
	private String address1;
	private String address2;
	private String city;
	private String province;
	private String country;
	
	private String skills;
	
//	@OneToMany(mappedBy = "userExperience", cascade = CascadeType.REMOVE)
//    private List<UserWorkExperience> userExperienceList;
	
	public UserProfile(//Long userId, 
			String name, String username, String password, Role role, boolean enabled, String address1, String address2,
		String city, String province, String country, String skills) {
		super(name, username, password, role, enabled);
		//this.userId = userId;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.province = province;
		this.country = country;
		this.skills = skills;
	}

	public UserProfile() {
	}

	
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//	public List<UserWorkExperience> getUserExperienceList() {
//		return userExperienceList;
//	}
//
//	public void setUserExperienceList(List<UserWorkExperience> userExperienceList) {
//		this.userExperienceList = userExperienceList;
//	}

	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
}
