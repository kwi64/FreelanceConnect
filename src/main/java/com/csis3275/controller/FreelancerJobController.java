package com.csis3275.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis3275.model.FreelancerJobService;
import com.csis3275.model.Job;
import com.csis3275.model.JobAggregate;
import com.csis3275.model.JobApplication;
import com.csis3275.model.JobApplicationService;
import com.csis3275.model.JobApplicationSkill;
import com.csis3275.model.JobApplicationSkillService;
import com.csis3275.model.JobApplicationWorkExperience;
import com.csis3275.model.JobApplicationWorkExperienceService;
import com.csis3275.model.UserPrincipal;

@Controller
public class FreelancerJobController {
	
	@Autowired
	private FreelancerJobService jobService;
	
	@Autowired
	private JobApplicationService jobApplicationService;
	
	@Autowired
	private JobApplicationWorkExperienceService jaWorkExperienceService;
	
	@Autowired
	private JobApplicationSkillService jaSkillService;
	
	
	@GetMapping(value={"/freelancer/jobs", "/freelancer/jobs/{id}"})
	public String jobs(Model model, @PathVariable Optional<Long> id) {
		model.addAttribute("view", "freelancer/jobs/jobs");
		model.addAttribute("style", true);
		model.addAttribute("script", true);
		
		List<Job> jobs = jobService.getJobPostings();
		LocalDate now = LocalDate.now();
		
		List<JobAggregate> jobAggregates = new ArrayList<JobAggregate>();
		for(Job job: jobs) {
			Period posted = Period.between(job.getValidityStartDate(), now);
			Period ending = Period.between(now, job.getValidityEndDate());
			
			if(!posted.isNegative() && !ending.isNegative()) {
				String postedOn = getPostedOnText(posted);
				
				jobAggregates.add(new JobAggregate(job, postedOn));
			}
		}
		
		if(id.isPresent()) {
			Job selected = jobService.getJobPosting(id.get());
			
			if(selected != null) {
				Period posted = Period.between(selected.getValidityStartDate(), now);
				String postedOn = getPostedOnText(posted);
				JobAggregate selectedJobAggregate = new JobAggregate(selected, postedOn);
				model.addAttribute("selected", selectedJobAggregate);
			}
		}
		
		model.addAttribute("jobs", jobAggregates);
		
		return "layout";
	}
	
	
	@GetMapping("/freelancer/jobs/{id}/apply")
	public String apply(Model model, @PathVariable(required = true) Long id) {
		model.addAttribute("view", "freelancer/apply/apply");
		model.addAttribute("style", true);
		model.addAttribute("script", true);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal)auth.getPrincipal();
		
		Job selected = jobService.getJobPosting(id);
		
		if(selected != null) {
			JobApplication application = jobApplicationService.getJobApplicationByUserIdAndJobId(principal.getId(), selected.getID());
			
			List<JobApplicationWorkExperience> workExperienceList = jaWorkExperienceService.getAllByJobApplicationId(application.getId());
			List<JobApplicationSkill> skillsList = jaSkillService.getAllByJobApplicationId(application.getId());
			
			model.addAttribute("workExperienceList", workExperienceList);
			model.addAttribute("skillList", skillsList);
			
			model.addAttribute("application", application);
		}else {
			return "redirect:/freelancer/jobs";
		}
		
		return "layout";
	}
	
	@PostMapping("/freelancer/jobs/{id}/apply")
	public String save(Model model, JobApplication application, RedirectAttributes attributes, @PathVariable(required = true) Long id) {
		model.addAttribute("view", "freelancer/apply/apply");
		model.addAttribute("style", true);
		model.addAttribute("script", true);
		
		jobApplicationService.updateJobApplication(application);
		
		if(application.getAction() != null && application.getAction().equals("addNewExperience")) {
			jaWorkExperienceService.createExperience(application, new JobApplicationWorkExperience());
		}
		
		if(application.getAction() != null && application.getAction().equals("addNewSkill")) {
			jaSkillService.createSkill(application, new JobApplicationSkill());
		}
		
		boolean submittedViaEnter = true;
		
		List<JobApplicationWorkExperience> workExperienceList = application.getWorkExperienceList();
		if(workExperienceList != null) {
			for(JobApplicationWorkExperience experience: workExperienceList) {
				if(experience.getAction() != null && experience.getAction().equals("delete")) {
					jaWorkExperienceService.deleteExperience(application, experience);
					submittedViaEnter = false;
					attributes.addFlashAttribute("success", "Experience successfully deleted!");
				}else {
					jaWorkExperienceService.updateExperience(application, experience);
				}
			}
		}
		
		List<JobApplicationSkill> skillList = application.getSkillList();
		if(skillList != null) {
			for(JobApplicationSkill skill: skillList) {
				if(skill.getAction() != null && skill.getAction().equals("delete")) {
					jaSkillService.deleteSkill(application, skill);
					submittedViaEnter = false;
					attributes.addFlashAttribute("success", "Skill successfully deleted!");
				}else {
					jaSkillService.updateSkill(application, skill);
				}
			}
		}
		
		if(submittedViaEnter == true && (application.getAction() == null || application.getAction().equals("save"))) {
			attributes.addFlashAttribute("success", "Successfully Saved!");
		}
		
		
		return "redirect:/freelancer/jobs/"+ id +"/apply";
	}
	
	
	private String getPostedOnText(Period posted) {
		String postedOn = "";
		if(posted.getYears() == 1) {
			postedOn = "Posted last year";
		}else if(posted.getYears() > 1) {
			postedOn = "Posted " + String.valueOf(posted.getYears()) + " years ago";
		}
		else if(posted.getMonths() == 1){
			postedOn = "Posted last month";
		}
		else if(posted.getMonths() > 1){
			postedOn = "Posted " + String.valueOf(posted.getMonths()) + " months ago";
		}else if(posted.getDays() == 0){
			postedOn = "Posted today";
		}
		else if(posted.getDays() == 1) {
			postedOn = "Posted yesterday";
		}
		else {
			postedOn = "Posted " + String.valueOf(posted.getDays()) + " days ago";
		}
		return postedOn;
	}
}
