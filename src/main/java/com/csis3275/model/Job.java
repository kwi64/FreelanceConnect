package com.csis3275.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Job {
	
	private String title;
	private String description;
	private BigDecimal wage;
	
	public Job(String jTitle, String jDesc, BigDecimal jWage)	{
		this.title = jTitle;
		this.description = jDesc;
		this.wage = jWage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getWage() {
		return wage;
	}

	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}

}
