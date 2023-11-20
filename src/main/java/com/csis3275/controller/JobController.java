package com.csis3275.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import com.csis3275.model.Job;


@Controller
public class JobController {

	private ArrayList<Job> jobList = new ArrayList<Job>();
	
	@GetMapping("/employer/viewJob")
	public String viewJob(Model model) {
		model.addAttribute("view", "employer/viewJob/viewJob");
		model.addAttribute("jobList", jobList);
		return "layout";
	}
	
	@GetMapping("/employer/createJob")
	public String createJob(Model model) {
		model.addAttribute("view", "employer/createJob/createJob");
		model.addAttribute("createJob",new Job());
		return "layout";
	}
	
	@PostMapping("/employer/createJob")
	public String addCreatedJob(Job createdJob)	{
		jobList.add(createdJob);
		return "redirect:/employer/viewJob";
	}
	
	@GetMapping("/employer/previewJob")
	public String previewJob(Model model) {
		model.addAttribute("view", "employer/previewJob/previewJob");
		return "layout";
	}
	
	@PostMapping("/employer/previewJob")
	public String previewJob(Job createdJob)	{
		return "redirect:/employer/viewJob";
	}
}
