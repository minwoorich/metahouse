package com.multi.metahouse.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("mypage/profile")
	public String profile() {
		return "mypage/profile";
	}
	
	@RequestMapping("join")
	public String join() {
		return "join";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}
	
	@GetMapping("mypage/delete/account")
	public String deleteAccount() {
		return "mypage/delete/account";
	}
	
	@GetMapping("mypage/create/portfolio")
	public String createPortfolio() {
		return "mypage/create/portfolio";
	}
	
	@GetMapping("mypage/setting")
	public String setting() {
		return "mypage/setting";
	}
}
