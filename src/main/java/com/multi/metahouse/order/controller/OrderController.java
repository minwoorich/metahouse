package com.multi.metahouse.order.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.multi.metahouse.domain.entity.order.dtoforjpa.AssetOrdersResponse;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersConfirmUpdateDTO;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.service.OrderService;
import com.multi.metahouse.point.service.PointService;
import com.multi.metahouse.review.service.ReviewService;

@Controller
@RequestMapping("/order")
public class OrderController {

	OrderService orderService;
	PointService pointService;
	ReviewService reviewService;

	@Autowired
	public OrderController(OrderService orderService, PointService pointService, ReviewService reviewService) {
		super();
		this.orderService = orderService;
		this.pointService = pointService;
		this.reviewService = reviewService;
	}

	// project 구매 관리
	@GetMapping("/project/buylist")
	public String projectBuylist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				List<ProjectOrdersResponse.Response> orderList = orderService.selectOrderListForBuyer(user.getUserId(),
						Integer.parseInt(pageNo));
				for (ProjectOrdersResponse.Response order : orderList) {
					System.out.println("order : " + order);
				}

				// 주문 상태별 개수 구해서 Map에 저장
				if (orderList != null && orderList.size()>0) {
					Map<String, Integer> orderCount = new HashMap<>();
					int count1 = 0;
					int count2 = 0;
					int count3 = 0;
					int count4 = 0;
					for (ProjectOrdersResponse.Response order : orderList) {
						if ("주문대기중".equals(order.getOrderStatus())) {
							count1++;
						} else if ("주문취소".equals(order.getOrderStatus())) {
							count2++;
						} else if ("진행중".equals(order.getOrderStatus())) {
							count3++;
						} else {
							count4++;
						}
					}
					orderCount.put("pending", count1);
					orderCount.put("cancelled", count2);
					orderCount.put("proceeding", count3);
					orderCount.put("completion", count4);
					model.addAttribute("orderCount", orderCount);
				}
				
				//리뷰 작성 여부
				if (orderList != null && orderList.size()>0) {
					for(ProjectOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countByOrderIdAndWriterId(order.getOrderId(), ((User)session.getAttribute("loginUser")).getUserId());
//						System.out.println("reviewCheck : "+reviewCheck + ", orderId : " + order.getOrderId());
						order.setReviewCheck(reviewCheck);
					}
				}
				model.addAttribute("orderList", orderList);
				return "order/project_buylist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/project/buylist/ -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// 프로젝트 주문 구매관리 - 카테고리
	@GetMapping("/project/buylist/category")
	public String projectBuylistCategory(Model model, HttpSession session, String pageNo, String category1,
			String category2, String category4, String category5) {

		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");

				List<ProjectOrdersResponse.Response> orderList = orderService.selectOrderListForBuyer(user.getUserId(),
						category1, category2, LocalDateTime.parse(category4, DateTimeFormatter.ISO_DATE_TIME),
						LocalDateTime.parse(category5, DateTimeFormatter.ISO_DATE_TIME), Integer.parseInt(pageNo));

				// 주문 상태별 개수 구해서 Map에 저장
				if (orderList != null) {
					Map<String, Integer> orderCount = new HashMap<>();
					int count1 = 0;
					int count2 = 0;
					int count3 = 0;
					int count4 = 0;
					for (ProjectOrdersResponse.Response order : orderList) {
						if ("주문대기중".equals(order.getOrderStatus())) {
							count1++;
						} else if ("주문취소".equals(order.getOrderStatus())) {
							count2++;
						} else if ("진행중".equals(order.getOrderStatus())) {
							count3++;
						} else {
							count4++;
						}
					}
					orderCount.put("pending", count1);
					orderCount.put("cancelled", count2);
					orderCount.put("proceeding", count3);
					orderCount.put("completion", count4);
					model.addAttribute("orderCount", orderCount);
				}
				//리뷰 작성 여부
				if (orderList != null && orderList.size()>0) {
					for(ProjectOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countByOrderIdAndWriterId(order.getOrderId(), ((User)session.getAttribute("loginUser")).getUserId());
//						System.out.println("reviewCheck : "+reviewCheck + ", orderId : " + order.getOrderId());
						order.setReviewCheck(reviewCheck);
					}
				}
				model.addAttribute("orderList", orderList);
				return "order/project_buylist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/project/buylist/category -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// project 주문 구매확정
	@PostMapping("/project/buylist/update.do")
	public String projectUpdate(HttpSession session, String orderStatus, String orderId) {
		System.out.println("---------------오더 컨트롤러--------------");
		System.out.println("orderStatus :  " + orderStatus + ", orderId : " + orderId);

		String url = "";
		if (session.getAttribute("loginUser") != null) {
			ProjectOrdersConfirmUpdateDTO dto = ProjectOrdersConfirmUpdateDTO.builder()
					.orderId(Long.parseLong(orderId))
					.orderStatus(orderStatus).build();

			// 업데이트 서비스 호출
			orderService.updateOrder(dto);
			url = "redirect:/order/project/buylist?pageNo=0";
		} else {
			url = "/login";
		}

		return url;
	}

	// project 판매 관리
	@GetMapping("/project/saleslist")
	public String projectSalelist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				List<ProjectOrdersResponse.Response> orderList = orderService.selectOrderListForSeller(user.getUserId(),
						Integer.parseInt(pageNo));
				
				for (ProjectOrdersResponse.Response order : orderList) {
					System.out.println("order : " + order);
				}

				// 주문 상태별 개수 구해서 Map에 저장
				if (orderList != null) {
					Map<String, Integer> orderCount = new HashMap<>();
					int count1 = 0;
					int count2 = 0;
					int count3 = 0;
					int count4 = 0;
					for (ProjectOrdersResponse.Response order : orderList) {
						if ("주문대기중".equals(order.getOrderStatus())) {
							count1++;
						} else if ("주문취소".equals(order.getOrderStatus())) {
							count2++;
						} else if ("진행중".equals(order.getOrderStatus())) {
							count3++;
						} else {
							count4++;
						}
					}
					orderCount.put("pending", count1);
					orderCount.put("cancelled", count2);
					orderCount.put("proceeding", count3);
					orderCount.put("completion", count4);
					model.addAttribute("orderCount", orderCount);
				}
				model.addAttribute("orderList", orderList);
				return "order/project_saleslist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/project/saleslist/ -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// 프로젝트 주문 판매관리 - 카테고리
	@GetMapping("/project/saleslist/category")
	public String projectSalesListCategory(Model model, HttpSession session, String pageNo, String category1,
			String category2, String category4, String category5) {

		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");

				List<ProjectOrdersResponse.Response> orderList = orderService.selectOrderListForSeller(user.getUserId(),
						category1, category2, LocalDateTime.parse(category4, DateTimeFormatter.ISO_DATE_TIME),
						LocalDateTime.parse(category5, DateTimeFormatter.ISO_DATE_TIME), Integer.parseInt(pageNo));

				// 주문 상태별 개수 구해서 Map에 저장
				if (orderList != null) {
					Map<String, Integer> orderCount = new HashMap<>();
					int count1 = 0;
					int count2 = 0;
					int count3 = 0;
					int count4 = 0;
					for (ProjectOrdersResponse.Response order : orderList) {
						if ("주문대기중".equals(order.getOrderStatus())) {
							count1++;
						} else if ("주문취소".equals(order.getOrderStatus())) {
							count2++;
						} else if ("진행중".equals(order.getOrderStatus())) {
							count3++;
						} else {
							count4++;
						}
					}
					orderCount.put("pending", count1);
					orderCount.put("cancelled", count2);
					orderCount.put("proceeding", count3);
					orderCount.put("completion", count4);
					model.addAttribute("orderCount", orderCount);
				}
				model.addAttribute("orderList", orderList);
				return "order/project_saleslist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/project/saleslist/ -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// project 주문 - 판매관리 (주문 승인 or 주문 거절)
	@PostMapping("/project/saleslist/update.do")
	public String projectOrderAccept(HttpSession session, String orderStatus, String orderId, String acceptanceValue) {
		System.out.println("---------------오더 컨트롤러--------------");
		System.out.println("orderStatus :  " + orderStatus + ", orderId : " + orderId);

		String url = "";
		if (session.getAttribute("loginUser") != null) {
			ProjectOrdersConfirmUpdateDTO dto = ProjectOrdersConfirmUpdateDTO.builder()
					.orderId(Long.parseLong(orderId))
					.orderStatus(orderStatus)
					.acceptanceValue(acceptanceValue)
					.build();
			System.out.println("진행중 DTO : " + dto);

			// 업데이트 서비스 호출
			orderService.updateOrder(dto);

			url = "redirect:/order/project/saleslist?pageNo=0";
		} else {
			url = "/login";
		}

		return url;
	}
//////////에셋 파트//////////////////////////////////////
	// asset 구매 관리 - 첫 화면(카테고리X)
	@GetMapping("/asset/buylist")
	public String assetBuylist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				List<AssetOrdersResponse.Response> orderList = 
						orderService.selectAssetOrderListForBuyer(
								user.getUserId(),
						Integer.parseInt(pageNo));
				
				//리뷰 작성 여부
				if (orderList != null && orderList.size()>0) {
					for(AssetOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countAssetReviewByOrderIdAndWriterId(order.getOrderId(), ((User)session.getAttribute("loginUser")).getUserId());
						System.out.println("에셋 리뷰 체크 : " + reviewCheck);
						order.setReviewCheck(reviewCheck);
					}
				}
				model.addAttribute("orderList", orderList);
				return "order/asset_buylist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/asset/buylist/ -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// asset 구매 관리 - 카테고리
	@GetMapping("/asset/buylist/category")
	public String assetBuylistCategory(Model model, HttpSession session, String pageNo, String category1,
			String category2, String category4, String category5) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				category4 +=" 00:00:00";
				category5 +=" 00:00:00";
				List<AssetOrdersResponse.Response> orderList = orderService.selectAssetOrderListForBuyer(user.getUserId(),
						category1, category2, LocalDateTime.parse(category4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(category5, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Integer.parseInt(pageNo));

				
				//리뷰 작성 여부
				if (orderList != null && orderList.size()>0) {
//					for(AssetOrdersResponse.Response order : orderList) {
//						Long reviewCheck = reviewService.countByOrderIdAndWriterId(order.getOrderId(), ((User)session.getAttribute("loginUser")).getUserId());
//						order.setReviewCheck(reviewCheck);
//					}
					for(AssetOrdersResponse.Response order : orderList) {
						order.setReviewCheck(0L);
					}
				}
				System.out.println("오더 컨트롤러 orderList : " + orderList);
				model.addAttribute("orderList", orderList);
				return "order/asset_buylist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/asset/buylist/category -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// project 판매 관리
	@GetMapping("/asset/saleslist")
	public String assettSalelist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				List<AssetOrdersResponse.Response> orderList = orderService.selectAssetOrderListForSeller(user.getUserId(),
						Integer.parseInt(pageNo));
				
				for (AssetOrdersResponse.Response order : orderList) {
					System.out.println("order : " + order);
				}

				model.addAttribute("orderList", orderList);
				return "order/asset_saleslist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/asset/saleslist/ -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}

	// asset 판매 관리 - 카테고리
	@GetMapping("/asset/saleslist/category")
	public String assetSalesCategory(Model model, HttpSession session, String pageNo, String category1,
			String category2, String category4, String category5) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				category4 +=" 00:00:00";
				category5 +=" 00:00:00";
				List<AssetOrdersResponse.Response> orderList = orderService.selectAssetOrderListForSeller(user.getUserId(),
						category1, category2, LocalDateTime.parse(category4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(category5, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), Integer.parseInt(pageNo));

				
				System.out.println("오더 컨트롤러 orderList : " + orderList);
				model.addAttribute("orderList", orderList);
				return "order/asset_saleslist";
			} else {
				return "redirect:/login";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("/asset/sales/category -> 에러발생");
			e.printStackTrace();
			return "redirect:/login";
		}
	}
	
	/*----------------------------------- OSE ------------------------------------*/
//	ajax-asset 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/asset")
	@ResponseBody
	public int orderA(@RequestBody ObjectNode saveObj, HttpSession session)
			throws JsonProcessingException, IllegalArgumentException {
		User loginUser = (User) session.getAttribute("loginUser");
		ObjectMapper mapper = new ObjectMapper();
		AssetOrdersDTO assetOrder = mapper.treeToValue(saveObj.get("assetOrder"), AssetOrdersDTO.class);
		int consumeAmount = mapper.treeToValue(saveObj.get("consumeAmount"), Integer.TYPE);
		System.out.println(assetOrder);
		System.out.println(consumeAmount);
		int reseult = orderService.orderA(assetOrder, loginUser, consumeAmount);
		return reseult;
	}

//	ajax-project 구매완료(구매 정보 저장하기)
	@RequestMapping(value = "/project")
	@ResponseBody
	public int orderP(@RequestBody ObjectNode saveObj, HttpSession session)
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
		//내역 생성
		int reseult = orderService.orderP(projectOrder, options, loginUser, consumeAmount);
		return reseult;
	}
}
