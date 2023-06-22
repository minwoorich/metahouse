package com.multi.metahouse.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectPackageSingleForm;
import com.multi.metahouse.domain.dto.project.ProjectPackageTripleForm;

@Controller
public class ProjectController {
	////////////////////// 승언님 파트////////////////////////
//	프로젝트 마켓 상품목록 보기
	@RequestMapping("project/main")
	public String gigs1() {
		return "project/main";
	}

//	프로젝트 상세보기
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
	//프로젝트 설명 입력하는 페이지 반환
	@GetMapping("project/forms/descriptions")
	public String writeForm(HttpSession session) {
		session.setAttribute("creator_id", "정민우");
		// 맨처음 들어온경우, 세션에 DTO넣어주고 뷰 리턴
		/*
		 * if (session.getAttribute("projectForm") == null) { // [추후 삭제]이건 임의로 넣어줌. 세희님이
		 * 로그인 완성하시면 삭제해야할 코드 ProjectFormDTO projectForm = new ProjectFormDTO();
		 * session.setAttribute("projectForm", projectForm); }
		 */
		return "project/projectform01";
	}
	
	//패키지 + 추가옵션 설정하는 페이지 반환
	@GetMapping("project/forms/packages")
	public String writePakcageForm(HttpSession session) {
		System.out.println("session : " + session.getAttribute("projectForm"));
		return "project/projectform02";
	}

	@GetMapping("project/forms/preview")
	public String getFormPreview(HttpSession session) {
		System.out.println("projectForm = "+session.getAttribute("projectForm"));
		System.out.println("projectPackageSingleForm = "+session.getAttribute("projectPackageSingleForm"));
		return "project/projectform03";
	}
	
	
	@RequestMapping(value="project/forms-ajax",produces="application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(
			HttpSession session, 
			ProjectFormDTO projectForm,
			@RequestParam List<String> filePath) {
		//세션 저장
		session.setAttribute("filePath", filePath);
		session.setAttribute("projectForm", projectForm);
		System.out.println("filePath = " + filePath);
		return  "/metahaus/project/forms/packages";
	}
	
	@RequestMapping(value="project/single-package-ajax",produces="application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(@RequestBody ProjectPackageSingleForm projectPackageSingleForm, 
			HttpSession session ) {
		System.out.println("projectPackageSingleForm = "+projectPackageSingleForm);
		//세션 저장
		session.setAttribute("projectPackageSingleForm", projectPackageSingleForm);
		
		return "/metahaus/project/forms/preview";
	}
	@RequestMapping(value="project/triple-package-ajax",produces="application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax( @RequestBody ProjectPackageTripleForm projectPackageTripleForm, 
			HttpSession session ) {
		System.out.println("projectPackageTripleForm = "+projectPackageTripleForm);
		//세션 저장
		session.setAttribute("projectPackageTripleForm", projectPackageTripleForm);
		
		return "/metahaus/project/forms/preview";
	}
	@PostMapping("project/forms")
	public String insertForm(HttpSession session) {
//		List<MultipartFile> imageList = session.getAttribute("imageList");
		return "redirect:main/index";
	}
	
	////////////////////승민님 파트//////////////////////////
	@GetMapping("project/project-product")
	public String projectFormlist() {
		return "project/project_product_list";
	}
	///////////////////창훈님 파트///////////////////////

}