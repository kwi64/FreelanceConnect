package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.UserPrincipal;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserServiceImpl;

@Controller
public class ManageProfileController {

	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/freelancer/manage-profile") 
	public String manageProfile(Model model) {
		model.addAttribute("view", "freelancer/profile/manage_profile");
		model.addAttribute("userProfile", userService.getUserProfileInfo((long) 1));
		/*
		String email = "", password = "", name = "";
		Boolean isFreelancer = true;
		Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if (auth!=null) {
			UserPrincipal user = (UserPrincipal)((org.springframework.security.core.Authentication) auth).getPrincipal();
			 email = user.getUsername();
			 password = user.getPassword();
			 isFreelancer = user.isFreelancer();
			 name = user.getDisplayName();
		}
		*/
		return "layout";
	}
	
	@GetMapping("/freelancer/delete-profile")
	public String deleteUser(@RequestParam("deleteuser") String id)	{
		userService.deleteUser(Long.parseLong(id));
		return "redirect:/login";	
	}
	
	@GetMapping("/freelancer/add-info")
	public String addInfo(@RequestParam("id") Long id, Model model) {
		model.addAttribute("view", "freelancer/profile/add_info");
		model.addAttribute("script", true);
		model.addAttribute("createInfo", userService.getUserProfileInfo(id));
		return "layout";
	}
	
	@PostMapping("/freelancer/add-info")
	public String addInfo(UserProfile newInfo) {
		userService.updateInfo(newInfo);
		return "redirect:/freelancer/manage-profile";
	}
	
	
	
}
