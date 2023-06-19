package com.multi.metahouse.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;

@Controller
public class PortfolioController {
	
	@GetMapping("mypage/portfolio")
	public String portfolio() {
		return "portfolio/portfolio_detail";
	}
	
	@GetMapping("mypage/create_portfolio")
	public String createPortfolio() {
		return "portfolio/create_portfolio";
	}
	
	@PostMapping("mypage/create_portfolio")
	public String createPortfolio(PortfolioDTO portfoliodto) {
		return "portfolio/create_portfolio";
	}
	
	@GetMapping("mypage/update_portfolio")
	public String updatePortfolio() {
		return "portfolio/update_portfolio";
	}
	
	@PostMapping("mypage/update_portfolio")
	public String updatePortfolio(PortfolioDTO portfoliodto) {
		return "portfolio/update_portfolio";
	}
	
	@PostMapping("mypage/delete_portfolio")
	public String deletePortfolio(HttpSession session) {
		return "redirect:profile";
	}


}
