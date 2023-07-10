package com.multi.metahouse.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.user.OtherProfileInfoDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.portfolio.Portfolio;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.portfolio.service.PortfolioService;
import com.multi.metahouse.user.service.GoogleService;
import com.multi.metahouse.user.service.KakaoService;
import com.multi.metahouse.user.service.NaverService;
import com.multi.metahouse.user.service.UserFileUploadLogicService;
import com.multi.metahouse.user.service.UserService;

@Controller
public class UserController {
	@Value("${file.directory}")
	private String uploadPath;
	
	
	UserService service;
	NaverService naverService;
	KakaoService kakaoService;
	GoogleService googleService;
	PortfolioService portfolioservice;
	ResourceLoader resourceLoader;
	UserFileUploadLogicService fileuploadservice;
	
	@Autowired
	public UserController(UserService service, NaverService naverService, KakaoService kakaoService,
			GoogleService googleService, PortfolioService portfolioservice, ResourceLoader resourceLoader,
			UserFileUploadLogicService fileuploadservice) {
		super();
		this.service = service;
		this.naverService = naverService;
		this.kakaoService = kakaoService;
		this.googleService = googleService;
		this.portfolioservice = portfolioservice;
		this.resourceLoader = resourceLoader;
		this.fileuploadservice = fileuploadservice;
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
			view = "redirect:main/index";
			
		}else {
			view ="redirect:login";
		}
		login.setViewName(view);
		
		return login;
	}
	
	
	//로그아웃 기능
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		User userInfo = (User) session.getAttribute("loginUser");
		
		if(userInfo.getSocialName() == "kakao") {
			kakaoService.logout((String)session.getAttribute("access_token"));
		}
		
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
	public String signup(User user, HttpSession session) {
		user.setUserGrade("Normal");
		
		service.insert(user);
		System.out.println(user);
		
		if(session != null) {
			session.invalidate();	
		}
		
		return "user/signupFin";
	}
	
	//내가 지금 인터셉터를 걸었기 때문에 인터셉터로 안걸릴 링크로 이것도 같이 추가해줘야함!!!!!!!
	@RequestMapping(value = "/idcheck", produces = "application/text;charset=utf-8", method = RequestMethod.GET)
	@ResponseBody 
	public String idCheck(String userId) {
		//System.out.println(userId);
		String msg ="";
		boolean check = service.idcheck(userId);
		if(!check) {
			//기존 DB에 저장되어 있지 않은 아이디
			msg = "사용가능아이디";
		}else {
			//기존 DB에 저장되어 있는 아이디
			msg = "사용불가능아이디";
		}
		System.out.println(msg);
		return msg; // 서버에서 텍스트만 보내주겠다는 뜻 (데이터 통신) application/text
	}
	
	@GetMapping("signupFin")
	public String signupFin() {
		return "user/signupFin";
	}
	
	@GetMapping("mypage/setting")
	public String setting() {
		return "user/setting";
	}
	
	@PostMapping("mypage/setting")
	public String setting(@RequestPart(value = "multipartfile") MultipartFile multipartfile, User user, HttpSession session) throws IOException {
		
		String path = uploadPath + "userThumbnail";
		String thumbnailStoreFilename = fileuploadservice.uploadFile(multipartfile, path);
		user.setThumbnailStoreFilename(thumbnailStoreFilename);
		System.out.println(user.getThumbnailStoreFilename());
		
		user.setUserId(((User)session.getAttribute("loginUser")).getUserId());
		System.out.println(user.getUserId());
		
		User loginUser = service.update(user);
		session.setAttribute("loginUser", loginUser);
		
		return "redirect:profile";
	}
	
	@GetMapping("mypage/update_password")
	public String updatePassword() {
		return "user/update_password";
	}
	
	@PostMapping("mypage/update_password")
	public String updatePassword(String new_password, HttpSession session) {
		System.out.println(new_password);
		String userId = ((User)session.getAttribute("loginUser")).getUserId();
		
		service.updatePassword(new_password, userId);
		
		
		if(session != null) {
			User userInfo = (User) session.getAttribute("loginUser");
			if(userInfo.getSocialName() == "kakao") {
				kakaoService.logout((String)session.getAttribute("access_token"));
				session.invalidate();
			}else {
				session.invalidate();
			}
		}
		return "user/login";
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
			if(userInfo.getSocialName().equals("kakao")) {
				kakaoService.unlink((String)session.getAttribute("access_token"));
				
			}else if(userInfo.getSocialName().equals("naver")) {
				if(session.getAttribute("access_token") != null) {
					naverService.unlink((String)session.getAttribute("access_token"));
				}
			}
			
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
		ModelAndView profile = new ModelAndView("user/profile");
		String userId = ((User)session.getAttribute("loginUser")).getUserId();
		List<PortfolioDTO> portfolioList = portfolioservice.selectPortfolioList(userId);
		System.out.println(portfolioList);
		profile.addObject("portfolioList", portfolioList);
		return profile;
	}
	
	@GetMapping("user/profile")
	public ModelAndView otherProfile(String userId) {
		System.out.println(userId);
		ModelAndView profileOther = new ModelAndView("user/profile_other");
		List<PortfolioDTO> portfolioList = portfolioservice.selectPortfolioList(userId);
		OtherProfileInfoDTO otherProfileInfo = service.read(userId);
		List<ProjectDTO> projectInfo = otherProfileInfo.getProjectInfo();
		
		projectInfo.forEach((e)->{
		Integer sp = e.getSingle_price();
			if( sp != null) {
				e.setPrice(sp);
			}else {
				e.setPrice(e.getTriple_price());
			}
	        
		});
		
		otherProfileInfo.setProjectInfo(projectInfo);
		
		System.out.println(portfolioList);
		System.out.println(otherProfileInfo);
		
		profileOther.addObject("portfolioList", portfolioList);
		profileOther.addObject("otherProfileInfo", otherProfileInfo);
		return profileOther;
	}
	
	
	//NAVER 로그인
	@RequestMapping(value = "/signnaver", method = RequestMethod.GET)
	public String naverLogin(@RequestParam(value = "code") String code, @RequestParam(value = "state") String state, Model model, HttpSession session) throws IOException {
		//System.out.println(code);
		//System.out.println(state);
		String view = "";
		
		//Access Token 생성
		OAuth2AccessToken oauthToken;
		oauthToken = naverService.getAccessToken(code, state);
		//System.out.println(oauthToken);
		String access_token = oauthToken.getAccessToken();
		session.setAttribute("access_token", access_token);
		
		//2. 로그인 사용자 정보 읽기
		String jsonUserInfo = naverService.getUserProfile(oauthToken);
		//System.out.println(jsonUserInfo);
		
		HashMap<String, String> userInfo = naverService.getUserInfo(jsonUserInfo);
		String naverLoginId = userInfo.get("id");
		
		User loginUser = service.socialLogin(naverLoginId, "naver");
		
		if(loginUser != null && loginUser.getSocialLoginId().equals(naverLoginId)) {
			session.setAttribute("loginUser", loginUser);
			view = "redirect:main/index";
		} else {
			model.addAttribute("nickname", userInfo.get("nickname"));
			model.addAttribute("email", userInfo.get("email"));
			model.addAttribute("id", userInfo.get("id"));
			model.addAttribute("gender", userInfo.get("gender"));
			model.addAttribute("socialName", "naver");
			model.addAttribute("mobile", userInfo.get("mobile"));
			model.addAttribute("birth", userInfo.get("birth"));
			
			view = "user/SNSsignup";
			
		}
		
		return view;
	}
	
	// 카카오 로그인
	@RequestMapping(value = "/signkakao", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model,
			HttpSession session) throws Exception {
		String view = "";
		String access_Token = kakaoService.getAccessToken(code);
		HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
		String kakaoLoginId = (String) userInfo.get("id");

		System.out.println(kakaoLoginId);
		
		User loginUser = service.socialLogin(kakaoLoginId, "kakao");
		//만약 카카오 로그인하는데 DB에 KaKao 토큰이 있을때
		if (loginUser != null && loginUser.getSocialLoginId().equals(kakaoLoginId)) {
			session.setAttribute("access_token", access_Token);
			session.setAttribute("loginUser", loginUser);
			view = "redirect:main/index";
		} else {
			//만약 카카오 로그인하는데 토근을 호출하고 KaKao 토큰번호가 DB에 없을떄
			model.addAttribute("nickname", userInfo.get("nickname"));
			model.addAttribute("email", userInfo.get("email"));
			model.addAttribute("id", userInfo.get("id"));
			model.addAttribute("gender", (String)userInfo.get("gender"));
			model.addAttribute("socialName", "kakao");
			model.addAttribute("mobile", null);
			model.addAttribute("birth", null);
			
			view = "user/SNSsignup";
			
		}
		return view;

	}
	
	// 구글 로그인
	@GetMapping(value = "/signgoogle", produces = "application/json")
	public String googleLogin(@RequestParam String code, Model model, HttpSession session) {
		String view = "";
		HashMap<String, String> userInfo =  googleService.socialLogin(code);
		String googleId = userInfo.get("id");
		System.out.println(googleId);
		
		User loginUser = service.socialLogin(googleId, "google");
		if (loginUser != null && loginUser.getSocialLoginId().equals(googleId)) {
			session.setAttribute("loginUser", loginUser);
			view = "redirect:main/index";
		} else {
			//만약 카카오 로그인하는데 토근을 호출하고 KaKao 토큰번호가 DB에 없을떄
			model.addAttribute("nickname", userInfo.get("nickname"));
			model.addAttribute("email", userInfo.get("email"));
			model.addAttribute("id", userInfo.get("id"));
			model.addAttribute("gender", null);
			model.addAttribute("socialName", "google");
			model.addAttribute("mobile", null);
			model.addAttribute("birth", null);
			view = "user/SNSsignup";
		}
		return view;
	}
	
	/* 관리자의 계정 삭제 요청 */
	@GetMapping
	public void deleteAccount(String userId) {
		// target 체크
		System.out.println("deleteAccount() 실행");
		System.out.println("target 'userId' : " + userId);
		
		service.delete(userId);
		
	}
}
