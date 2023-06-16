package com.multi.metahouse.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PortfolioController {
	@GetMapping("mypage/portfolio")
	public String portfolio() {
		return "user/portfolio_detail";
	}
	
	@GetMapping("mypage/create/portfolio")
	public String createPortfolio() {
		return "user/create_portfolio";
	}
}
