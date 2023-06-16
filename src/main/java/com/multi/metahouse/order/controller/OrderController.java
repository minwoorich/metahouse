package com.multi.metahouse.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	// project 구매 관리
	@GetMapping("/project/buylist")
	public String projectBuylist() {
		return "order/project_buylist";
	}
	// project 판매 관리	
	@GetMapping("/project/saleslist")
	public String projectSalelist() {
		return "order/project_saleslist";
	}
	
	// asset 구매 관리
	@GetMapping("/asset/buylist")
	public String assetBuylist() {
		return "order//asset_buylist";
	}
	
	// asset 구매 관리	
	@GetMapping("/asset/saleslist")
	public String assetSalelist() {
		return "order/asset_saleslist";
	}
}
