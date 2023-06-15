package com.multi.metahouse.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.project.service.ProjectFileService;
import com.multi.metahouse.project.service.ProjectOptionService;
import com.multi.metahouse.project.service.ProjectService;

@Controller
@RequestMapping("/project")
public class ProjectControllerMinwoo {
	ProjectService projectService;
	ProjectFileService projectFileService;
	ProjectOptionService projectOptionService;
	
	
	@Autowired
	public ProjectControllerMinwoo(ProjectService projectService, ProjectFileService projectFileService,ProjectOptionService projectOptionService) {
		super();
		this.projectService = projectService;
		this.projectFileService = projectFileService;
		this.projectOptionService = projectOptionService;
	}


	@GetMapping("/project-product")
	public String projectFormlist() {
		return "market_sm/project/mypage_project_product_list";
	}
	
	
	
	
	
	/////////////////민우 영역/////////////////////
	@GetMapping("/project-form-01")
	public String insertProjectForm01(Model model) {
		ProjectDTO projectDto = new ProjectDTO();
		model.addAttribute("projectDto", projectDto);
		return "redirect:project/projectform01";
	}
	@PostMapping("/project-form-01")
	public String projectForm01Page() {
		return "project/projectform02";
	}
	
	@GetMapping("/project-form-02")
	public String projectForm02() {
		return "project/projectform02";
	}
	@GetMapping("/project-form-03")
	public String projectForm03() {
		return "project/projectform03";
	}
}
