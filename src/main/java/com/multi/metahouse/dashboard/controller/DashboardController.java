package com.multi.metahouse.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.metahouse.dashboard.service.DashboardService;
import com.multi.metahouse.domain.dto.search.UserSearchResultDTO;
import com.multi.metahouse.domain.entity.user.User;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
	
	DashboardService service;
	
	@Autowired
	public DashboardController(DashboardService service) {
		this.service = service;
	}
	
	/* View Mapping */
	@GetMapping("/")
	public String viewDashboardMain(Model model) {
		return "dashboard/content/dashboard_statistics";
	}
	
	@GetMapping("/member/management")
	public String viewMemberManagement(Model model,
			@RequestParam(defaultValue = "1") int page
			) {
		
		
		int size = 10;
		PageRequest pageReq = PageRequest.of(page - 1, size, Sort.by("createDate").descending());
		
		Page<User> userPage = null;
		
		
		userPage = service.findALL(pageReq);
		System.out.println(userPage.getSize());
		int totalPage = userPage.getTotalPages();
		List<Integer> pageBtnList = makePagingSeq(page , 5 , totalPage);
		System.out.println(pageBtnList);
		model.addAttribute("userList", userPage);
		model.addAttribute("pages",pageBtnList);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPage",totalPage);
		
		
		return "dashboard/content/member_management";
	}
	
	@GetMapping("/member/search")
	public String viewMemberSearch(Model model) {
		return "dashboard/content/member_search";
	}
	
	@PostMapping("/member/search")
	@ResponseBody
	public List<User> search_user(String keyword) {
		System.out.println(keyword);
		List<User> userList = service.findByUserIdLike(keyword);
		
		return userList;
	}
	
	@GetMapping("/member/update")
	public String viewMemberUpdate(Model model) {
		return "dashboard/content/member_update";
	}
	@PostMapping("/member/update")
	public String update_member(UserSearchResultDTO user) {
		service.user_update(user);
		return "redirect:/dashboard/member/search";
	}
	
	
	@GetMapping("/member/report")
	public String viewMemberReport(Model model) {
		return "dashboard/content/member_report";
	}
	
	@GetMapping("/statistics")
	public String viewStatistics(Model model) {
		return "dashboard/content/dashboard_statistics";
	}
	
	
	
	
	
////////////////////////////////////
	
   public static List<Integer> makePagingSeq(int n, int size , int max){
	   
	      List<Integer> seq = new ArrayList<Integer>();
	      int pageIndex = (n-1)/size;
	      int start = pageIndex*size;
	      
	      for(int i= start+1; i<= start+size; i++) {
	         if(i> max) break;
	         seq.add(i);
	      }
	      
	      return seq;
   }
////////////////////////////////////
}