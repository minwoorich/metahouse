package com.multi.metahouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@GetMapping("/")
	public String viewDashboardMain(Model model) {
		return "dashboard/content/dash_statistics";
	}
	
	@GetMapping("/member/management")
	public String viewMemberManagement(Model model) {
		return "dashboard/content/member_management";
	}
	
	@GetMapping("/member/search")
	public String viewMemberSearch(Model model) {
		return "dashboard/content/member_search";
	}
	
	@GetMapping("/member/update")
	public String viewMemberUpdate(Model model) {
		return "dashboard/content/member_update";
	}
	
	@GetMapping("/member/report")
	public String viewMemberReport(Model model) {
		return "dashboard/content/member_report";
	}
	
	@GetMapping("/statistics")
	public String viewStatistics(Model model) {
		return "dashboard/content/dash_statistics";
	}
}