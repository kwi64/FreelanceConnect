package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.UserPrincipal;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;
import com.csis3275.model.UserServiceImpl;

@Controller
public class ManageProfileController {

	@Autowired
	private UserServiceImpl userService;
	@Autowired
	private UserService userService2;
	
	@GetMapping("/freelancer/manage-profile") 
	public String manageProfile(Model model) {
		model.addAttribute("view", "freelancer/profile/manage_profile");
		//still hard coded must change soon
		model.addAttribute("userProfileInfo", userService.getUserProfileInfo((long) 1));
		model.addAttribute("user", userService2.getUserInfo((long) 1));
		return "layout";
	}
	
	@GetMapping("/freelancer/delete-profile")
	public String deleteUser(@RequestParam("deleteuser") String id)	{
		userService.deleteUser(Long.parseLong(id));
		return "redirect:/login";	
	}
	
	@GetMapping("/freelancer/add-info/{id}")
	public String addInfo(@PathVariable(required = true) Long id, Model model) {
		model.addAttribute("view", "freelancer/profile/add_info");
		model.addAttribute("script", true);
		model.addAttribute("createInfo", userService.getUserProfileInfo(id));
		model.addAttribute("updateUser" , userService2.getUserInfo(id));
		return "layout";
	}
	
	@PostMapping("/freelancer/add-info/{id}")
	public String addInfo(@PathVariable(required = true) Long id, UserProfile newInfo) {
		userService.updateInfo(newInfo);
		//userService2.updateInfo(newInfo);
		return "redirect:/freelancer/manage-profile";
	}
	
	
	
}
