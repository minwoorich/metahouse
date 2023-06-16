package com.multi.metahouse.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectOptionFormDTO;
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
	@GetMapping("/forms/descriptions")
	public String writeForm(Model model, HttpSession session) {
		//맨처음 들어온경우, 세션에 DTO넣어주고 뷰 리턴
		if(session.getAttribute("projectForm")==null) {
			session.setAttribute("userId", "creator");
			ProjectFormDTO projectForm = new ProjectFormDTO();
			session.setAttribute("projectForm", projectForm);
		}
		return "project/projectform01";
	}
	
	
	@GetMapping("/forms/options")
	public String writeOptionForm(HttpSession session) {
		if(session.getAttribute("projectOptionForm")==null) {
			ProjectOptionFormDTO projectOptionForm = new ProjectOptionFormDTO();
			session.setAttribute("projectOptionForm", projectOptionForm);
		}
		return "project/projectform02";
	}
	
	@GetMapping("/forms/preview")
	public String getFormPreview() {
		return "project/projectform03";
	}
	@RequestMapping(value="main/index", method=RequestMethod.POST)
	public String insertForm(HttpSession session) {
//		List<MultipartFile> imageList = session.getAttribute("imageList");
		return "redirect:";
	}
}
