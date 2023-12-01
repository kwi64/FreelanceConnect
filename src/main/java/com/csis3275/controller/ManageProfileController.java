package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.IUserWorkExperience;
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
	
	private IUserWorkExperience userExperienceRepo;
	
	
	
	@GetMapping("/freelancer/manage-profile") 
	public String manageProfile(Model model) {
		model.addAttribute("view", "freelancer/profile/manage_profile");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		long id = principal.getId();
		
		model.addAttribute("userProfileInfo", userService.getUserProfileInfo(id));
		model.addAttribute("user", userService2.getUserInfo(id));
		//model.addAttribute("createExperience", userExperience.getUserExperience(id));
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
		
		UserWorkExperience createdExperience = (UserWorkExperience) userService2.createUser(new UserWorkExperience(
				newInfo.getName(), newInfo.getUsername(), newInfo.getPassword(), 
				newInfo.getRole(),true,null,null,null,null));
		userExperienceRepo.save(createdExperience);
		
		//userExperience.updateExperience(newInfo, id);
		return"redirect:/freelancer/manage-profile";
	}
	
	
	
}
