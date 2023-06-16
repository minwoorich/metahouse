package com.multi.metahouse.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectOptionFormDTO;

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
	@GetMapping("project/forms/descriptions")
	public String writeForm(Model model, HttpSession session) {
		// 맨처음 들어온경우, 세션에 DTO넣어주고 뷰 리턴
		if (session.getAttribute("projectForm") == null) {
			// [추후 삭제]이건 임의로 넣어줌. 세희님이 로그인 완성하시면 삭제해야할 코드
			session.setAttribute("userId", "creator");
			ProjectFormDTO projectForm = new ProjectFormDTO();
			session.setAttribute("projectForm", projectForm);
		}
		return "project/projectform01";
	}

	@GetMapping("project/forms/options")
	public String writeOptionForm(HttpSession session) {
		if (session.getAttribute("projectOptionForm") == null) {
			ProjectOptionFormDTO projectOptionForm = new ProjectOptionFormDTO();
			session.setAttribute("projectOptionForm", projectOptionForm);
		}
		return "project/projectform02";
	}

	@GetMapping("project/forms/preview")
	public String getFormPreview() {
		return "project/projectform03";
	}

	@RequestMapping(value = "main/index", method = RequestMethod.POST)
	public String insertForm(HttpSession session) {
//		List<MultipartFile> imageList = session.getAttribute("imageList");
		return "redirect:";
	}
	
	////////////////////승민님 파트//////////////////////////
	@GetMapping("project/project-product")
	public String projectFormlist() {
		return "market_sm/project/mypage_project_product_list";
	}
	///////////////////창훈님 파트///////////////////////

}