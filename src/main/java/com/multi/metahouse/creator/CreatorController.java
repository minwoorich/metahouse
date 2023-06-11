package com.multi.metahouse.creator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/creator")
public class CreatorController {
	@RequestMapping("/buylist")
	public String firstBuylist() {
		return "market_sm/creator/creator_buylist";
	}
	
	@RequestMapping("/saleslist")
	public String firstSalelist() {
		return "market_sm/creator/creator_saleslist";
	}
	
	@GetMapping("/creator-product")
	public String assetFormlist() {
		return "market_sm/creator/mypage_creator_product_list";
	}
}
