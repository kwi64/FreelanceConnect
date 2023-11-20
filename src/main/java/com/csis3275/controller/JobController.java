package com.csis3275.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import com.csis3275.model.Job;
import com.csis3275.model.JobService;


@Controller
public class JobController {

	@Autowired
	private JobService jobService;
	private ArrayList<Job> jobList = new ArrayList<Job>();
	
	@GetMapping("/employer/viewJob")
	public String viewJob(Model model) {
		model.addAttribute("view", "employer/viewJob/viewJob");
		model.addAttribute("jobList", jobService.listJobs());
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
		jobService.createJob(createdJob);
		jobList.add(createdJob);
		return "redirect:/employer/previewJob";
	}
	
	@GetMapping("/employer/previewJob")
	public String previewJob(Model model) {
		model.addAttribute("view", "employer/previewJob/previewJob");
		var preview = jobList.get(jobList.size() - 1) ;
		model.addAttribute("preview", preview);
		return "layout";
	}
	
	@PostMapping("/employer/previewJob")
	public String previewJob(Job createdJob)	{
		jobList.clear();
		return "redirect:/employer/viewJob";
	}
}
