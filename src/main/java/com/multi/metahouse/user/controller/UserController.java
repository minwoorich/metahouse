package com.multi.metahouse.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.user.service.UserService;

@Controller
public class UserController {
	UserService service;
	
	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}
	
	//로그인 페이지
	@GetMapping("login")
	public String login() {
		return "user/login";
	}
	
	//로그인 기능
	@PostMapping("login")
	public ModelAndView login(String userId, String password, HttpServletRequest request) {
		ModelAndView login = new ModelAndView();
		String view = null;
		
		User loginUser = service.login(userId, password);
		
		if(loginUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			view = "redirect:mypage/profile";
			
		}else {
			view ="redirect:login";
		}
		login.setViewName(view);
		
		return login;
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if(session != null) {
			session.invalidate();
		}
		return "redirect:login";
	}
	
	@RequestMapping("join")
	public String join() {
		return "user/join";
	}
	
	//회원가입 페이지
	@GetMapping("signup")
	public String signup() {
		return "user/signup";
	}
	
	//회원가입 기능
	@PostMapping("signup")
	public String signup(User user) {
		service.insert(user);
		System.out.println(user);
		return "user/login";
	}
	
	@GetMapping("mypage/setting")
	public String setting() {
		return "user/setting";
	}
	
	@PostMapping("mypage/setting")
	public String setting(User user) {
		return ("redirect:profile");
	}
	
	
	@GetMapping("mypage/delete_account")
	public String deleteAccount() {
		return "user/delete_account";
	}
	
	@PostMapping(value = "mypage/delete_account", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String deleteAccount(String userId, String password, HttpSession session) {
		String returnMsg = null;
		//로그인한 유져 정보
		User userInfo = (User) session.getAttribute("loginUser");
		//session을 통해 비밀번호 받아옴.
		String loginUserPw = userInfo.getPassword();
		
		System.out.println(password);
		System.out.println(loginUserPw.equals(password));
		
		if(password.equals(loginUserPw)) {
			returnMsg = "success";
			service.delete(userId);
			
			if(session != null) {
				session.invalidate();
			}
		}else {
			returnMsg = "fail";
		}
		
		return returnMsg;
	}
	
	@RequestMapping("mypage/profile")
	public ModelAndView profile(HttpSession session) {
		ModelAndView profileView = new ModelAndView("user/profile");
		
		//update로 값이 변경되면 session의 값과 table에 저장된 값이 달라지 때문에 read를 호출해 출력.
		String userId = ((User)session.getAttribute("loginUser")).getUserId();
		User userProfile = service.read(userId);
		profileView.addObject("userProfile", userProfile);
		
		return profileView;
	}
	
	@GetMapping("user/profile")
	public String otherProfile() {
		return "user/profile_other";
	}
}
