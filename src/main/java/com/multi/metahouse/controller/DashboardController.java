package com.multi.metahouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	@GetMapping("/index")
	public String viewIndex(Model model) {
		return "dashboard/content/index";
	}
	
	@GetMapping("/")
	public String viewDashboardMain(Model model) {
		return "dashboard/content/dash_statistics";
	}
	
	@GetMapping("/management")
	public String viewManagement(Model model) {
		return "dashboard/content/member_management";
	}
	
	@GetMapping("/report")
	public String viewReport(Model model) {
		return "dashboard/content/member_report";
	}
	
	@GetMapping("/statistics")
	public String viewStatistics(Model model) {
		return "dashboard/content/dash_statistics";
	}
}