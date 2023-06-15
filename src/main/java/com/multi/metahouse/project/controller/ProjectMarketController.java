package com.multi.metahouse.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProjectMarketController {

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

}
