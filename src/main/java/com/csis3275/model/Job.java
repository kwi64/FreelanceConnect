package com.csis3275.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Job {
	
	private String title;
	private String description;
	private BigDecimal wage;
	private String[] tags;
	
	public Job(String jTitle,
			String jDesc,
			BigDecimal jWage,
			String[] jTags)	{
		this.title = jTitle;
		this.description = jDesc;
		this.wage = jWage;
		this.tags = jTags;
	}

}
