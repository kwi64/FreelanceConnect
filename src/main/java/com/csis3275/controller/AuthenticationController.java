package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.csis3275.model.UserProfile;
import com.csis3275.model.UserServiceImpl;

@Controller
public class AuthenticationController {
	
	@Autowired 
	private UserServiceImpl userService;
	
	@GetMapping("/login")
	public String login(Model model) {
//		model.addAttribute("view", "login/login");
//		model.addAttribute("script", true);
		return "login/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("createProfile", new UserProfile());
		return "register/register";
	}
	
	@PostMapping("/register")
	public String createProfile(UserProfile createProfile) {
		userService.createProfile(createProfile);
		return "redirect:/login";
	}
}
