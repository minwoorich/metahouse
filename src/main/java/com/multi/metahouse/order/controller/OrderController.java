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
import com.multi.metahouse.domain.entity.project.jpadto.ProjectListDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	// project 구매 관리
	@GetMapping("/project/buylist")
	public String projectBuylist(Model model, HttpSession session) {
		if(session.getAttribute("loginUser")!=null) {
			User user = (User)session.getAttribute("loginUser");
			
			return "order/project_buylist";
		}else {
			return "redirect:/login";
		}
		
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
	public void orderA(@RequestBody AssetOrdersDTO assetOrder) {
		System.out.println(assetOrder);
		orderService.orderA(assetOrder);
	}
	
//	ajax-project 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/project")
	@ResponseBody
	public void orderP(@RequestBody ObjectNode saveObj) throws JsonProcessingException, IllegalArgumentException {
		 ObjectMapper mapper = new ObjectMapper();
		 ProjectOrdersDTO projectOrder = mapper.treeToValue(saveObj.get("projectOrder"), ProjectOrdersDTO.class);
		 List<SelectedAddOptionDTO> options = new ArrayList<SelectedAddOptionDTO>();
		 JsonNode JsonOptions = saveObj.get("options");
		 
		 for(int i=0; i<JsonOptions.size(); i++){
				JsonNode json = mapper.createObjectNode();
				json = JsonOptions.get(i);
				SelectedAddOptionDTO option = mapper.treeToValue(json, SelectedAddOptionDTO.class);
				options.add(option);
			}
		 int result = orderService.orderP(projectOrder, options);
		 
		 System.out.println("주문내역"+projectOrder);
		 System.out.println("추가옵션"+options);
		 System.out.println("생성결과"+result);
	}
}
