package com.multi.metahouse.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
	// project 구매 관리
	@GetMapping("/project/buylist")
	public String firstBuylist() {
		return "market_sm/project/project_buylist";
	}
	// project 판매 관리	
	@GetMapping("/project/saleslist")
	public String firstSalelist() {
		return "market_sm/project/project_saleslist";
	}
	
	// asset 구매 관리	
	@GetMapping("/asset/saleslist")
	public String assetSale() {
		return "market_sm/asset/asset_saleslist";
	}
	
	// asset 판매 관리
	@GetMapping("/asset/buylist")
	public String assetBuy() {
		return "market_sm/asset/asset_buylist";
	}
}
