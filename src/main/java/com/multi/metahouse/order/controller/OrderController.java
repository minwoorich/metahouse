package com.multi.metahouse.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.domain.dto.order.OrderDTO;
import com.multi.metahouse.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	OrderService service;
	
	@Autowired
	public OrderController(OrderService service) {
		super();
		this.service = service;
	}
	// project 구매 관리
	@GetMapping("/project/buylist")
	public String projectBuylist() {
		return "order/project_buylist";
	}
	// project 판매 관리	
	@GetMapping("/project/saleslist")
	public String projectSalelist() {
		return "order/project_saleslist";
	}
	
	// asset 구매 관리
	@GetMapping("/asset/buylist")
	public String assetBuylist() {
		return "order/asset_buylist";
	}
	
	// asset 구매 관리	
	@GetMapping("/asset/saleslist")
	public String assetSalelist() {
		return "order/asset_saleslist";
	}
	
	@RequestMapping("/asset/category")
	public String category(Model model) {
		List<OrderDTO> orderlist = service.orderlist();
		model.addAttribute("category1", orderlist);
		
		return "order/asset_category";
	}
}
