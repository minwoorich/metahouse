package com.multi.metahouse.order.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.service.AssetCategoryService;
import com.multi.metahouse.order.service.OrderService;
import com.multi.metahouse.point.service.PointService;

@Controller
@RequestMapping("/order")
public class OrderController {

	AssetCategoryService service;
	OrderService orderService;
	PointService pointService;

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

	/*----------------------------------- OSE ------------------------------------*/
//	ajax-asset 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/asset")
	@ResponseBody
	public void orderA(@RequestBody ObjectNode saveObj, HttpSession session)
			throws JsonProcessingException, IllegalArgumentException {
		User loginUser = (User) session.getAttribute("loginUser");
		ObjectMapper mapper = new ObjectMapper();
		AssetOrdersDTO assetOrder = mapper.treeToValue(saveObj.get("assetOrder"), AssetOrdersDTO.class);
		int consumeAmount = mapper.treeToValue(saveObj.get("consumeAmount"), Integer.TYPE);
		System.out.println(assetOrder);
		System.out.println(consumeAmount);
		orderService.orderA(assetOrder, loginUser, consumeAmount);
	}

//	ajax-project 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/project")
	@ResponseBody
	public void orderP(@RequestBody ObjectNode saveObj, HttpSession session)
			throws JsonProcessingException, IllegalArgumentException {
		User loginUser = (User) session.getAttribute("loginUser");
		ObjectMapper mapper = new ObjectMapper();
		int consumeAmount = mapper.treeToValue(saveObj.get("consumeAmount"), Integer.TYPE);
		ProjectOrdersDTO projectOrder = mapper.treeToValue(saveObj.get("projectOrder"), ProjectOrdersDTO.class);
		List<SelectedAddOptionDTO> options = new ArrayList<SelectedAddOptionDTO>();
		JsonNode JsonOptions = saveObj.get("options");

		for (int i = 0; i < JsonOptions.size(); i++) {
			JsonNode json = mapper.createObjectNode();
			json = JsonOptions.get(i);
			SelectedAddOptionDTO option = mapper.treeToValue(json, SelectedAddOptionDTO.class);
			options.add(option);
		}
		// int result = orderService.orderP(projectOrder, options, loginUser,
		// consumeAmount);

		System.out.println("주문내역" + projectOrder);
		System.out.println("추가옵션" + options);
		System.out.println("구매자" + loginUser);
		System.out.println("결제금액" + consumeAmount);
		// System.out.println("결과" + result);

	}
}
