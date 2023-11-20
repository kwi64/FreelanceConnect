package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.UserProfile;
import com.csis3275.model.UserServiceImpl;

@Controller
public class ManageProfileController {

	@Autowired
	private UserServiceImpl userService;
	
	
	@GetMapping("/manage-profile") 
	public String manageProfile(Model model) {
		model.addAttribute("view", "profile/manage_profile");
		model.addAttribute("userProfile", userService.getUserProfileInfo((long) 1));
		return "layout";
	}
	
	@GetMapping("/delete-profile")
	public String deleteUser(@RequestParam("deleteuser") String id)	{
		userService.deleteUser(Long.parseLong(id));
		//userService.deleteUserLocation(Long.parseLong(id));
		return "redirect:/login";	
	}
	
	@GetMapping("/add-info")
	public String addInfo(@RequestParam("id") Long id, Model model) {
		model.addAttribute("view", "profile/add_info");
		model.addAttribute("createInfo", userService.getUserProfileInfo(id));
		return "layout";
	}
	
	@PostMapping("/add-info")
	public String addInfo(UserProfile newInfo) {
		userService.updateInfo(newInfo);
		return "redirect:/manage-profile";
	}
	
	
	
}
