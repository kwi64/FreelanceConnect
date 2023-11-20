package com.csis3275.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	}
	
	@GetMapping("/createJob")
	public String createJob(Model model) {
		model.addAttribute("view", "createJob/createJob");
		model.addAttribute("jobList", jobList);
		return "layout";
	}
	
}
