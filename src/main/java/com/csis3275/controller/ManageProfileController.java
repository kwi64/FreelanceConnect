package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.UserLocation;
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
		model.addAttribute("userLocation", userService.getUserLocationInfo((long) 1));
		return "layout";
	}

	@GetMapping("/edit-profile")
	public String editProfile(Model model) {
		model.addAttribute("view", "profile/edit_profile");
		model.addAttribute("editUser", userService.getUserProfileInfo((long) 1));
		return "profile/edit_profile";
	}
	
	@GetMapping("/delete-profile")
	public String deleteUser(@RequestParam("deleteuser") String id)	{
		userService.deleteUser(Long.parseLong(id));
		userService.deleteUserLocation(Long.parseLong(id));
		return "redirect:/login";	
	}
	
	@GetMapping("/add-info")
	public String addInfo(Model model) {
		model.addAttribute("view", "profile/add_info");
		model.addAttribute("createInfo", new UserLocation());
		return "layout";
	}
	
	@PostMapping("/add-info")
	public String addInfo(UserLocation newInfo, Model model) {
		model.addAttribute("view", "profile/add_info");
		userService.addInfo(newInfo);
		return "redirect:/manage-profile";
	}
	
	/*
	@PutMapping("/edit-profile")
	public String editProfile(@RequestBody UserProfile updatedUser, Model model) {
		model.addAttribute("view", "profile/edit_profile");
		userService.updateUser(updatedUser);
		return "redirect:/manage-profile";
	}*/
	
	
}
