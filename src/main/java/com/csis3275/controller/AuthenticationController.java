package com.csis3275.controller;

import org.springframework.security.web.savedrequest.SavedRequest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.csis3275.model.User;
import com.csis3275.model.UserDAO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {

	@Autowired
	private UserDAO userDAO;

	@GetMapping("/login")
	public String login(Model model, @RequestParam("error") Optional<String> error) {

		if (error.isPresent()) {
			model.addAttribute("error", error.get());
		}

		model.addAttribute("user", new User());
		return "login/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register/register";
	}

	@PostMapping("/register")
	public String doRegister(User user, Model model) {

		User existing = userDAO.findUserByUsername(user.getUsername());
		if (existing != null) {

			user.setPassword("");
			model.addAttribute("user", user);
			model.addAttribute("username", "This email is already registerd!");

			return "register/register";
		}

		userDAO.createUser(user);
		model.addAttribute("user", new User());

		model.addAttribute("success", "Account successfully created!. <a href='/login'><i>Please log in!</i></a>");
		return "register/register";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		return "redirect:/login";
	}

	@GetMapping("/")
	public String dashboard(Model model) {

		model.addAttribute("view", "dashboard/dashboard");
		model.addAttribute("script", true);
		return "layout";
	}
	
	@GetMapping("/access_denied")
	public String accessDenied(HttpServletRequest request, HttpServletResponse response) {
		return "403";
	}
}
