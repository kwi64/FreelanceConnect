package com.csis3275.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalController {
	@ModelAttribute("g_path")
	String getRequestServletPath(HttpServletRequest request) {
		return request.getServletPath();
	}
}
