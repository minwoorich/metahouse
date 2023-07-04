package com.multi.metahouse.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.service.OrderService;
import com.multi.metahouse.point.service.PointService;

@Controller
@RequestMapping("/order")
public class OrderController {

	OrderService orderService;
	PointService pointService;

	@Autowired
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}

	// project 구매 관리
		@GetMapping("/project/buylist")
		public String projectBuylist(Model model, HttpSession session, String pageNo) {
			System.out.println("페이지 번호 : " + pageNo);
			try {
				if(session.getAttribute("loginUser")!=null) {
					User user = (User)session.getAttribute("loginUser");
					List<ProjectOrdersResponse.BuyerResponse> orderList = orderService.selectOrderListForBuyer(user.getUserId(), Integer.parseInt(pageNo));
					//주문 상태별 개수 구해서 Map에 저장
					if(orderList!=null) {
						Map<String, Integer> orderCount = new HashMap<>();
						int count1=0;
						int count2=0;
						int count3=0;
						int count4=0;
						for(ProjectOrdersResponse.BuyerResponse order : orderList) {
							if("주문대기중".equals(order.getOrderStatus())) {
								count1++;
							}else if("주문취소".equals(order.getOrderStatus())) {
								count2++;
							}else if("진행중".equals(order.getOrderStatus())) {
								count3++;
							}else {
								count4++;
							}
						}
						orderCount.put("pending", count1);
						orderCount.put("cancelled", count2);
						orderCount.put("proceeding", count3);
						orderCount.put("completion", count4);
						model.addAttribute("orderCount",orderCount);
					}
					model.addAttribute("orderList",orderList);
					return "order/project_buylist";
				}else {
					return "redirect:/login";
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("/project/buylist/ -> 에러발생");
				e.printStackTrace();
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
