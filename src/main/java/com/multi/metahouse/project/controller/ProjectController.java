package com.multi.metahouse.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectPackageSingleForm;
import com.multi.metahouse.domain.dto.project.ProjectPackageTripleForm;
import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.project.service.ProjectFileUploadLogicService;
import com.multi.metahouse.project.service.ProjectService;

@Controller
public class ProjectController {
	ProjectFileUploadLogicService fileService;
	ProjectService projectService;

	@Autowired
	public ProjectController(ProjectFileUploadLogicService fileService, ProjectService projectService) {
		super();
		this.fileService = fileService;
		this.projectService = projectService;
	}

	////////////////////// 승언님 파트////////////////////////
//	프로젝트 마켓 상품목록 보기
	@RequestMapping("project/main")
	public String gigs1() {
		return "project/main";
	}

	// 프로젝트 상세보기
	@RequestMapping("project/detail")
	public String showGigs1() {
		return "project/market_detail";
	}

//	프로젝트 구매하기
	@GetMapping("project/purchase")
	public String puchaseGigs() {
		return "order/project_purchase";
	}

//	프로젝트 구인 상품목록 보기
	@RequestMapping("project/main2")
	public String gigs2() {
		return "project/z_project2_main";
	}

//	프로젝트 구인 상세페이지
	@RequestMapping("project/detail2")
	public String showGigs2() {
		return "project/z_project2_detail";
	}

	/////////////////////////// 민우님 파트////////////////////////////////
	// 프로젝트 설명 입력하는 페이지 반환
	@GetMapping("project/forms/descriptions")
	public String writeForm(HttpSession session) {
		session.setAttribute("creator_id", "정민우");
		
		return "project/projectform01";
	}

	// 패키지 + 추가옵션 설정하는 페이지 반환
	@GetMapping("project/forms/packages")
	public String writePakcageForm(HttpSession session) {
		System.out.println("session : " + session.getAttribute("projectForm"));
		return "project/projectform02";
	}

	@GetMapping("project/forms/preview")
	public String getFormPreview(HttpSession session, Model model) {
		
		// 세션에 삼단패키지가 들어갔는지 ,단일패키지가 들어갔는지
		if (session.getAttribute("projectPackageTripleForm") == null) {
			model.addAttribute("package", "single");
			List<ProjectAddOption> optionList = ((ProjectPackageSingleForm) session
					.getAttribute("projectPackageSingleForm")).getProjectAddOptionList();
			model.addAttribute("optionList", optionList);
		} else {
			model.addAttribute("package", "triple");
			List<ProjectAddOption> optionList = ((ProjectPackageTripleForm) session
					.getAttribute("projectPackageTripleForm")).getProjectAddOptionList();
			model.addAttribute("optionList", optionList);
		}

		return "project/projectform03";
	}

	@RequestMapping(value = "project/forms-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(HttpSession session, ProjectFormDTO projectForm) {
		
		// 세션 저장
		session.setAttribute("projectForm", projectForm);
		
		
		return "/metahaus/project/forms/packages";
	}

	@RequestMapping(value = "project/single-package-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(@RequestBody ProjectPackageSingleForm projectPackageSingleForm,
			HttpSession session) {
		
		// 세션 초기화(projectPackageSingelForm 데이터 세션에서 삭제)
		session.removeAttribute("projectPackageSingleForm");
		// 세션 저장
		session.setAttribute("projectPackageSingleForm", projectPackageSingleForm);

		return "/metahaus/project/forms/preview";
	}

	@RequestMapping(value = "project/triple-package-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(@RequestBody ProjectPackageTripleForm projectPackageTripleForm,
			HttpSession session) {
		
		// 세션 초기화(projectPackageTripleForm 데이터 세션에서 삭제)
		session.removeAttribute("projectPackageTripleForm");
		// 세션 저장
		session.setAttribute("projectPackageTripleForm", projectPackageTripleForm);

		return "/metahaus/project/forms/preview";
	}

	@GetMapping("project/forms")
	public String insertForm(HttpSession session) throws IllegalStateException, IOException {
		List<ProjectContentsEntity> fileDtoList = new ArrayList<>();
		// 세션에서 데이터 추출
		ProjectFormDTO projectForm = (ProjectFormDTO) session.getAttribute("projectForm");
		
		// 썸네일 추출  & 업로드 & DTO의 thumbnailPath 세팅
		MultipartFile thumbnail = projectForm.getThumbnail();
		String thumbnailPath = fileService.uploadFile(thumbnail); 
		
		//상세이미지들 추출 & 업로드 & ProjectContentsEntity 데이터 옮겨담기
		if (!projectForm.getDetailImages().isEmpty()) {
			// 상세이미지들 추출 
			List<MultipartFile> detailImages = projectForm.getDetailImages();
			// 상세이미지들 업로드
			fileDtoList = fileService.uploadFiles(detailImages); 
		}

		// 1) ProjectEntity에 데이터 옮겨담기
		ProjectEntity projectEntity = ProjectEntity.toEntity(projectForm);//DTO->엔티티 
		projectEntity.setThumbnail(thumbnailPath);
		
		
				
		// 단일패키지 or 삼단패키지
		if (session.getAttribute("projectPackageSingleForm") != null) {
			ProjectPackageSingleForm projectPackageSingleForm = 
					(ProjectPackageSingleForm) session.getAttribute("projectPackageSingleForm");

		} else {
			ProjectPackageTripleForm projectPackageTripleForm = 
					(ProjectPackageTripleForm) session.getAttribute("projectPackageTripleForm");
		}
		return "main/index";
	}

	//////////////////// 승민님 파트//////////////////////////
	@GetMapping("project/project-product")
	public String projectFormlist() {
		return "project/project_product_list";
	}
	/////////////////// 창훈님 파트///////////////////////

}