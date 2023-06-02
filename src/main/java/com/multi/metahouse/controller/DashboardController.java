package com.multi.metahouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	@GetMapping("/")
	public String index() {
		return "Thymeleaf/index";
	}
	
	@GetMapping("/dashboard")
	public String viewDashboard(Model model, String tab) {
		model.addAttribute("tab", tab);
		return "Thymeleaf/main_layout";
	}
}





