package com.csis3275.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis3275.model.IUserRepository;
import com.csis3275.model.Role;
import com.csis3275.model.User;
import com.csis3275.model.UserPrincipal;
import com.csis3275.model.UserProfile;
import com.csis3275.model.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthenticationController {

	@Autowired
	private IUserRepository userProfileRepository; 
	
	@Autowired
	private UserService userDAO;

	@GetMapping("/login")
	public String login(Model model) {

		model.addAttribute("user", new User());
		return "login/login";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register/register";
	}

	@PostMapping("/register")
	public String doRegister(User user, Model model, RedirectAttributes attributes) {
		
		user.setUsername(user.getUsername().trim());
		
		User existing = userDAO.findUserByUsername(user.getUsername());
		if (existing != null) {

			user.setPassword("");
			model.addAttribute("user", user);
			model.addAttribute("username", "This email is already registerd!");

			return "register/register";
		}
		User createdUser = userDAO.createUser(new UserProfile(
				user.getName(), user.getUsername(), user.getPassword(), 
				user.getRole(),true,null,null,null,null,null,null
				));
		userProfileRepository.save(createdUser);
		
		attributes.addFlashAttribute("user", new User());
		attributes.addFlashAttribute("success", "Account successfully created!. Please log in!");
		
		return "redirect:/login";
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
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal)auth.getPrincipal();
		
		if(principal.isFreelancer()) {
			return "redirect:/freelancer/jobs";
		}
		
		model.addAttribute("view", "dashboard/dashboard");
		model.addAttribute("script", true);
		return "layout";
	}
	
	@GetMapping("/access_denied")
	public String accessDenied(HttpServletRequest request, HttpServletResponse response) {
		return "403";
	}
}
