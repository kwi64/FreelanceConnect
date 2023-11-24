package com.csis3275.model;

public class JobAggregate {
	private Job job;
	private String postedOn;
	
	public JobAggregate(Job job, String postedOn) {
		this.job = job;
		this.postedOn = postedOn;
	}
	public JobAggregate() {}
	
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public String getPostedOn() {
		return postedOn;
	}
	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}
	
	
}
