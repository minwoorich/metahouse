package com.multi.metahouse.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.order.service.AssetCategoryService;
import com.multi.metahouse.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	AssetCategoryService service;
	OrderService orderService;

	@Autowired
	public OrderController(AssetCategoryService service, OrderService orderService) {
		super();
		this.service = service;
		this.orderService = orderService;
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

	@GetMapping("/asset/category")
	public String assetCategory(Model model) {
		return null;
	}

	/*-----------------------------------------------------------------------*/
//	asset 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/asset")
	public String orderA(@RequestBody AssetOrdersDTO assetOrder) {
		System.out.println(assetOrder);
		int order = orderService.orderA(assetOrder);
		System.out.println(order);
		if (order == 1) {

		} else {

		}
		return "redirect:/order/asset/buylist";
	}
}
