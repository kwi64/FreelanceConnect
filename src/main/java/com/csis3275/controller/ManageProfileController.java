package com.csis3275.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.IUserRepository;
import com.csis3275.model.IUserWorkExperience;
import com.csis3275.model.JobApplicationWorkExperience;
import com.csis3275.model.User;
import com.csis3275.model.UserExperienceServiceImpl;
import com.csis3275.model.UserPrincipal;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;
import com.csis3275.model.UserServiceImpl;
import com.csis3275.model.UserWorkExperience;

@Controller
public class ManageProfileController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserService userService2;
	@Autowired
	private UserExperienceServiceImpl userExperience;
	
	@Autowired
	private IUserWorkExperience userExperienceRepo;
	
	@Autowired
	private IUserRepository userRepo;
	
	
	
	@GetMapping("/freelancer/manage-profile") 
	public String manageProfile(Model model) {
		model.addAttribute("view", "freelancer/profile/manage_profile");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		model.addAttribute("userProfileInfo", userService.getUserProfileInfo(id));
		model.addAttribute("user", userService2.getUserInfo(id));
		
		List<UserWorkExperience> workExperienceList = userExperience.getAllByUserId(id);
		
		model.addAttribute("createExperience", workExperienceList);
//			userExperience.getUserExperience(id));
		return "layout";
	}
	
	@GetMapping("/freelancer/delete-profile")
	public String deleteUser()	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		userService.deleteUser(id);
		return "redirect:/login";	
	}
	
	@GetMapping("/freelancer/add-info")
	public String addInfo(Model model) {
		model.addAttribute("view", "freelancer/profile/add_info");
		model.addAttribute("script", true);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		model.addAttribute("createInfo", userService.getUserProfileInfo(id));
		model.addAttribute("updateUser" , userService2.getUserInfo(id));
		return "layout";
	}
	
	@PostMapping("/freelancer/add-info")
	public String addInfo(UserProfile newInfo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		userService.updateInfo(newInfo, id);
		return "redirect:/freelancer/manage-profile";
	}
	
	@GetMapping("/freelancer/add-experience") 
	public String addExperience(Model model) {
		model.addAttribute("view", "freelancer/profile/add_experience");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		model.addAttribute("createExperience", userExperience.getUserExperience(id));
		return "layout";
	}
	
	@PostMapping("/freelancer/add-experience")
	public String addExperience(UserWorkExperience newInfo) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		User currUser = userService2.getUserInfo(id);
		
		userExperience.createUserExperience(new UserWorkExperience(currUser, newInfo.getTitle(), newInfo.getCompany(), newInfo.getDateOfHire(), newInfo.getDateOfQuit(), newInfo.isCurrentlyWorking()));
		return"redirect:/freelancer/manage-profile";
	}
	
	@GetMapping("/freelancer/delete-experiences")
	public String deleteAllExperience(UserWorkExperience expToDelete)	{
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		List<UserWorkExperience> workExperienceList = userExperience.getAllByUserId(id);
		
		userExperience.deleteUserExperiences(workExperienceList);
		return "redirect:/freelancer/manage-profile";	
	}
	
	@GetMapping("/freelancer/delete-experience/{id}")
	public String deleteExperience(@PathVariable(required=true ) Long id){
		
		userExperience.deleteUserExperience(id);
		return "redirect:/freelancer/manage-profile";	
	}
	
	
	
}
