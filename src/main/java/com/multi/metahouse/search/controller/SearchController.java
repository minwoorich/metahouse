package com.multi.metahouse.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.multi.metahouse.domain.dto.search.SearchFilter;
import com.multi.metahouse.domain.dto.search.ServiceSearchResultDTO;
import com.multi.metahouse.domain.dto.user.UserDTO;
import com.multi.metahouse.search.service.SearchService;

import lombok.NoArgsConstructor;

@Controller
@NoArgsConstructor
public class SearchController {
	
	private SearchService service;
	
	@Autowired
	public SearchController(SearchService service) {
		this.service = service;
	}
	
	
	@GetMapping("/search/result")
	public String show_search_result(Model model, SearchFilter filter) {
//		SearchFilter filter = new SearchFilter("제페토", "all", "pd", "배 제작");
//		List<ServiceSearchResultDTO> list = service.searchByFilter(filter);
//		
//		list.forEach((e)->{
//			System.out.println(e);
//		});
		System.out.println(filter);
		
		
		
		List<ServiceSearchResultDTO> serviceList = service.searchByFilter(filter);
		List<UserDTO> userList = service.searchUserByKeyword(filter.getKeyword());
		
		
		model.addAttribute("serviceList",serviceList);
		model.addAttribute("userList", userList);
		model.addAttribute("filter",filter);
		
		return "search/search";
	}
}
