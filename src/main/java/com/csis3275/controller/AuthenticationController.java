package com.csis3275.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
	@GetMapping("/login")
	public String login(Model model) {
//		model.addAttribute("view", "login/login");
//		model.addAttribute("script", true);
		return "login/login";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		return "register/register";
	}
}
