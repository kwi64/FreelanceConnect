package com.csis3275;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring6.SpringTemplateEngine;


@SpringBootApplication
public class FreelanceConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreelanceConnectApplication.class, args);
	}
	
//	@Bean
//	SpringTemplateEngine templateEngine() {
//	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//	    templateEngine.addDialect(new LayoutDialect());
//	    return templateEngine;
//	}
}
