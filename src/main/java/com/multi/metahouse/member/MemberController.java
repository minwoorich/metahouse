package com.multi.metahouse.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	@RequestMapping("mypage/profile")
	public String profile() {
		return "member/profile";
	}
	
	@RequestMapping("join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "member/signup";
	}
	
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("mypage/delete/account")
	public String deleteAccount() {
		return "member/delete_account";
	}
	
	@GetMapping("mypage/create/portfolio")
	public String createPortfolio() {
		return "member/create_portfolio";
	}
	
	@GetMapping("mypage/setting")
	public String setting() {
		return "member/setting";
	}
	
	@GetMapping("user/profile")
	public String otherProfile() {
		return "member/profile_other";
	}
}
