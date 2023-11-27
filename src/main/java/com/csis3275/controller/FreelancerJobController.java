package com.csis3275.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.csis3275.model.FreelancerJobService;
import com.csis3275.model.Job;
import com.csis3275.model.JobAggregate;
import com.csis3275.model.JobApplication;
import com.csis3275.model.JobApplicationService;
import com.csis3275.model.JobApplicationSkill;
import com.csis3275.model.JobApplicationSkillService;
import com.csis3275.model.JobApplicationStatus;
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

	@GetMapping(value = { "/freelancer/jobs", "/freelancer/jobs/{id}" })
	public String jobs(Model model, @PathVariable Optional<Long> id) {
		model.addAttribute("view", "freelancer/jobs/jobs");
		model.addAttribute("style", true);
		model.addAttribute("script", true);

		List<Job> jobs = jobService.getJobPostings();
		LocalDate now = LocalDate.now();

		List<JobAggregate> jobAggregates = new ArrayList<JobAggregate>();
		for (Job job : jobs) {
			Period posted = Period.between(job.getValidityStartDate(), now);
			Period ending = Period.between(now, job.getValidityEndDate());

			if (!posted.isNegative() && !ending.isNegative()) {
				String postedOn = getPostedOnText(posted);

				jobAggregates.add(new JobAggregate(job, postedOn));
			}
		}

		if (id.isPresent()) {
			Job selected = jobService.getJobPosting(id.get());

			if (selected != null) {
				Period posted = Period.between(selected.getValidityStartDate(), now);
				String postedOn = getPostedOnText(posted);
				JobAggregate selectedJobAggregate = new JobAggregate(selected, postedOn);
				model.addAttribute("selected", selectedJobAggregate);
				
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
				
				JobApplication application = jobApplicationService.getJobApplicationByUserIdAndJobId(principal.getId(), id.get());
				
				if(application.getId() == 0 || application.getApplicationStatus() == null || application.getApplicationStatus() == JobApplicationStatus.INPROGRESS) {
					model.addAttribute("canApply", true);
				}else {
					model.addAttribute("canApply", false);
				}
			}
		}

		model.addAttribute("jobs", jobAggregates);

		return "layout";
	}

	@GetMapping("/freelancer/jobs/{id}/apply")
	public String apply(Model model, @PathVariable(required = true) Long id) {
		model.addAttribute("view", "freelancer/apply/apply");
		model.addAttribute("script", true);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
		
		model.addAttribute("job_id", id);
		Job selected = jobService.getJobPosting(id);

		if (selected != null) {
			JobApplication application = jobApplicationService.getJobApplicationByUserIdAndJobId(principal.getId(),
					selected.getID());

			List<JobApplicationWorkExperience> workExperienceList = jaWorkExperienceService
					.getAllByJobApplicationId(application.getId());
			List<JobApplicationSkill> skillsList = jaSkillService.getAllByJobApplicationId(application.getId());

			model.addAttribute("workExperienceList", workExperienceList);
			model.addAttribute("skillList", skillsList);
			
			Map<String, String> errors = getApplicationStepOneErrors(application);
			
			model.addAttribute("hasErrors", errors.size() > 0);
			
			model.addAttribute("application", application);
			
			if(application.getId() == 0 || application.getApplicationStatus() == null || application.getApplicationStatus() == JobApplicationStatus.INPROGRESS) {
				model.addAttribute("canApply", true);
			}else {
				model.addAttribute("canApply", false);
			}
		} else {
			return "redirect:/freelancer/jobs";
		}

		return "layout";
	}

	@PostMapping("/freelancer/jobs/{id}/apply")
	public String save(JobApplication application, RedirectAttributes attributes,
			@PathVariable(required = true) Long id) {
		
		
		Map<String, String> errors = getApplicationStepOneErrors(application);
		
		if(errors.size() > 0) {
			attributes.addFlashAttribute("errors", errors);
		}
		

		application.setApplicationStatus(JobApplicationStatus.INPROGRESS);
		jobApplicationService.updateJobApplication(application);

		if (application.getAction() != null && application.getAction().equals("addNewExperience")) {
			jaWorkExperienceService.createExperience(application, new JobApplicationWorkExperience());
		}

		if (application.getAction() != null && application.getAction().equals("addNewSkill")) {
			jaSkillService.createSkill(application, new JobApplicationSkill());
		}

		boolean submittedViaEnter = true;

		List<JobApplicationWorkExperience> workExperienceList = application.getWorkExperienceList();
		if (workExperienceList != null) {
			for (JobApplicationWorkExperience experience : workExperienceList) {
				if (experience.getAction() != null && experience.getAction().equals("delete")) {
					jaWorkExperienceService.deleteExperience(application, experience);
					submittedViaEnter = false;
					attributes.addFlashAttribute("success", "Experience successfully deleted!");
				} else {
					jaWorkExperienceService.updateExperience(application, experience);
				}
			}
		}

		List<JobApplicationSkill> skillList = application.getSkillList();
		if (skillList != null) {
			for (JobApplicationSkill skill : skillList) {
				if (skill.getAction() != null && skill.getAction().equals("delete")) {
					jaSkillService.deleteSkill(application, skill);
					submittedViaEnter = false;
					attributes.addFlashAttribute("success", "Skill successfully deleted!");
				} else {
					jaSkillService.updateSkill(application, skill);
				}
			}
		}

		if (submittedViaEnter == true && (application.getAction() == null || application.getAction().equals("save"))) {
			attributes.addFlashAttribute("success", "Successfully Saved!");
		}

		if (application.getAction() != null && application.getAction().equals("next")) {
			if(errors.size() == 0) {
				return "redirect:/freelancer/jobs/" + id + "/upload";
			}
		}

		return "redirect:/freelancer/jobs/" + id + "/apply";
	}

	@GetMapping("/freelancer/jobs/{id}/upload")
	public String upload(Model model, @PathVariable(required = true) Long id) {
		model.addAttribute("view", "freelancer/upload/upload");
		model.addAttribute("script", true);
		
		model.addAttribute("job_id", id);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();

		JobApplication application = jobApplicationService.getJobApplicationByUserIdAndJobId(principal.getId(), id);

		if (application.getId() != 0) {

			model.addAttribute("application", application);
			
			if(application.getApplicationStatus() == null || application.getApplicationStatus() == JobApplicationStatus.INPROGRESS) {
				model.addAttribute("canApply", true);
			}else {
				model.addAttribute("canApply", false);
			}

		} else {
			return "redirect:/freelancer/jobs";
		}

		return "layout";
	}

	@PostMapping("/freelancer/jobs/{id}/upload")
	public String submit(@RequestParam("file") MultipartFile file, @RequestParam("coverLetter") String coverLetter,
			@PathVariable(required = true) Long id, RedirectAttributes attributes) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal principal = (UserPrincipal) auth.getPrincipal();

		JobApplication application = jobApplicationService.getJobApplicationByUserIdAndJobId(principal.getId(), id);

		String folderPath = String.format("uploads/%d/%d/", principal.getId(), id);
		String filePath = folderPath + file.getOriginalFilename();

		try {
			if (!file.isEmpty() && application.getResumePath() != null) {
				File existing = new File(application.getResumePath());
				existing.delete();
			}

			if (!file.isEmpty()) {
				Files.createDirectories(Paths.get(folderPath));
				FileUtils.cleanDirectory(new File(folderPath));

				byte[] bytes = file.getBytes();
				Files.write(Paths.get(filePath), bytes);

				application.setResumePath(filePath);
				application.setResumeFileName(file.getOriginalFilename());
			}

			application.setCoverLetter(coverLetter);
			application.setApplicationStatus(JobApplicationStatus.SUBMITTED);

			jobApplicationService.updateJobApplication(application);

			return "redirect:/freelancer/jobs/" + id;

		} catch (Exception ex) {
			attributes.addFlashAttribute("error", "Sorry, an unexpected error occured! Please try again.");
		}

		return "redirect:/freelancer/jobs/" + id + "/upload";
	}

	private String getPostedOnText(Period posted) {
		String postedOn = "";
		if (posted.getYears() == 1) {
			postedOn = "Posted last year";
		} else if (posted.getYears() > 1) {
			postedOn = "Posted " + String.valueOf(posted.getYears()) + " years ago";
		} else if (posted.getMonths() == 1) {
			postedOn = "Posted last month";
		} else if (posted.getMonths() > 1) {
			postedOn = "Posted " + String.valueOf(posted.getMonths()) + " months ago";
		} else if (posted.getDays() == 0) {
			postedOn = "Posted today";
		} else if (posted.getDays() == 1) {
			postedOn = "Posted yesterday";
		} else {
			postedOn = "Posted " + String.valueOf(posted.getDays()) + " days ago";
		}
		return postedOn;
	}
	
	private Map<String, String> getApplicationStepOneErrors(JobApplication application) {
		LocalDate now = LocalDate.now();
		Map<String, String> errors = new HashMap<String, String>();

		if(application.getName() == null || application.getName().isBlank()) {
			errors.put("name", "Please enter your full name.");
		}
		if(application.getAddressLine1() == null || application.getAddressLine1().isBlank()) {
			errors.put("addressLine1", "Address Line 1 is required.");
		}
		if(application.getCity() == null || application.getCity().isBlank()) {
			errors.put("city", "Enter your city.");
		}
		if(application.getProvince() == null || application.getProvince().isBlank()) {
			errors.put("province", "Enter your province.");
		}
		if(application.getCountry() == null || application.getCountry().isBlank()) {
			errors.put("country", "Enter your country.");
		}
		
		if(application.getWorkExperienceList() != null) {
			int i = 0;
			for(JobApplicationWorkExperience experience: application.getWorkExperienceList()) {
				if(experience.getTitle() == null || experience.getTitle().isBlank()) {
					errors.put("workExperienceList_"+ i +"_title", "Enter your job title.");
				}
				if(experience.getCompany() == null || experience.getCompany().isBlank()) {
					errors.put("workExperienceList_"+ i +"_company", "Enter your company.");
				}
				if(experience.getLocation() == null || experience.getLocation().isBlank()) {
					errors.put("workExperienceList_"+ i +"_location", "Enter company location.");
				}
				if(experience.getFromDate() == null) {
					errors.put("workExperienceList_"+ i +"_fromDate", "Employment start date is required.");
				}
				if(!experience.isCurrentlyWorking() && experience.getToDate() == null) {
					errors.put("workExperienceList_"+ i +"_toDate", "Employment end date is required.");
				}
				
				if(!experience.isCurrentlyWorking() && experience.getFromDate() != null && experience.getToDate() != null) {
					Period duration = Period.between(experience.getFromDate(), experience.getToDate());
					if(duration.isNegative()) {
						errors.put("workExperienceList_"+ i +"_fromDate", "Invalid date range selected.");
					}
				}
				
				if(experience.getFromDate() != null) {
					Period starting = Period.between(experience.getFromDate(), now);
					if(starting.isNegative()) {
						errors.put("workExperienceList_"+ i +"_fromDate", "Invalid start date.");
					}
				}
				
				if(experience.getToDate() != null) {
					Period ending = Period.between(experience.getToDate(), now);
					if(ending.isNegative()) {
						errors.put("workExperienceList_"+ i +"_toDate", "Invalid end date.");
					}
				}
				i++;
			}
		}
		
		if(application.getSkillList() != null) {
			int j = 0;
			for(JobApplicationSkill skill: application.getSkillList()) {
				if(skill.getName() == null || skill.getName().isBlank()) {
					errors.put("workExperienceList_"+ j +"_name", "Please enter a skill.");
				}
				j++;
			}
		}
		
		return errors;
	}
}
