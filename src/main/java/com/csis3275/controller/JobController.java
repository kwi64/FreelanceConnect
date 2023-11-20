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
	public JobController() {
		jobList.add(new Job((long) 1, "Software Engineer", "Electronic Arts", 
				"Vancouver, BC", "Be a software Engineer in Vancouver, BC", "1", "2", (long) 15.00));
		jobList.add(new Job((long) 2, "Software Engineer", "Electronic Arts", 
				"Vancouver, BC", "Be a software Engineer in Vancouver, BC", "1", "2", (long) 15.00));
		jobList.add(new Job((long) 3, "Software Engineer", "Electronic Arts", 
				"Vancouver, BC", "Be a software Engineer in Vancouver, BC", "1", "2", (long) 15.00));
		jobList.add(new Job((long) 4, "Software Engineer", "Electronic Arts", 
				"Vancouver, BC", "Be a software Engineer in Vancouver, BC", "1", "2", (long) 15.00));
		jobList.add(new Job((long) 5, "Software Engineer", "Electronic Arts", 
				"Vancouver, BC", "Be a software Engineer in Vancouver, BC", "1", "2", (long) 15.00));
	}
	
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
		return "layout";
	}
}
