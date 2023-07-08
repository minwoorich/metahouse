package com.multi.metahouse.order.controller;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
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
	@Value("${file.directory}")
	private String uploadPath;

	OrderService orderService;
	PointService pointService;
	ReviewService reviewService;
	AssetService assetService;
	AttachFileUploadLogicService fileService;

	@Autowired
	public OrderController(OrderService orderService, PointService pointService, ReviewService reviewService,
			AssetService assetService, AttachFileUploadLogicService fileService) {
		super();
		this.orderService = orderService;
		this.pointService = pointService;
		this.reviewService = reviewService;
		this.assetService = assetService;
		this.fileService = fileService;
	}

	// project 구매 관리
	@GetMapping("/project/buylist")
	public String projectBuylist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				User user = (User) session.getAttribute("loginUser");
				List<ProjectOrdersResponse.Response> orderList = orderService.selectOrderListForBuyer(user.getUserId(),
						Integer.parseInt(pageNo));

//				주문 상태별 개수 구해서 Map에 저장
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

				// 리뷰 작성 여부
				if (orderList != null && orderList.size() > 0) {
					for (ProjectOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countByOrderIdAndWriterId(order.getOrderId(),
								((User) session.getAttribute("loginUser")).getUserId());
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

//				주문 상태별 개수 구해서 Map에 저장
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

				// 리뷰 작성 여부
				if (orderList != null && orderList.size() > 0) {
					for (ProjectOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countByOrderIdAndWriterId(order.getOrderId(),
								((User) session.getAttribute("loginUser")).getUserId());
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
		String url = "";
		if (session.getAttribute("loginUser") != null) {
			ProjectOrdersConfirmUpdateDTO dto = ProjectOrdersConfirmUpdateDTO.builder().orderId(Long.parseLong(orderId))
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

//				주문 상태별 개수 구해서 Map에 저장
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

//				주문 상태별 개수 구해서 Map에 저장
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

	// 프로젝트 주문 - 판매관리 (주문 승인 or 주문 거절)
	@PostMapping("/project/saleslist/update.do")
	public String projectOrderAccept(HttpSession session, String orderStatus, String orderId, String acceptanceValue) {

		String url = "";
		if (session.getAttribute("loginUser") != null) {
			ProjectOrdersConfirmUpdateDTO dto = ProjectOrdersConfirmUpdateDTO.builder().orderId(Long.parseLong(orderId))
					.orderStatus(orderStatus).acceptanceValue(acceptanceValue).build();
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

	@RequestMapping("/asset/buylist/download")
	public ResponseEntity<UrlResource> downloadFile(HttpSession session, String assetId)
			throws MalformedURLException, FileNotFoundException {

		String fileUploadPath = uploadPath + "asset_attach_file/";

		// 1. 파일을 다운로드 하기 위해 DB에 저장된 파일의 정보를 가져오기 - 다운로드 요청된 파일을 response해줌
		List<AssetContentDTO> fileList = assetService.assetContentInfo(assetId);
		String storeFileName = fileList.get(0).getAsset_store_filename();
		int pos = storeFileName.lastIndexOf(".");
		String ext = storeFileName.substring(pos + 1);

		// 2. BoardFileDTO객체에서 다운로드 할 파일을 실제 객체로 변환
		// URLResource resource = new URLResource("file:"+ 파일의 real-path")
		// 업로드된 파일의 저장 위치와 실제 파일명을 연결해서 경로를 생성.
		UrlResource resource = new UrlResource("file:" + fileUploadPath + storeFileName);

		// 3. 오리지날파일명에 한글이 있는 경우 처리해줘야함
		String encodedFilename = UriUtils.encode("download." + ext, "UTF-8");

		// 4. 파일을 다운로드형식으로 응답하기 위한 응답헤더 세팅
		String mycontenttype = "attachment; filename=\"" + encodedFilename + "\"";

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, mycontenttype).body(resource);

	}

	// asset 구매 관리 - 첫 화면(카테고리X)
	@GetMapping("/asset/buylist")
	public String assetBuylist(Model model, HttpSession session, String pageNo) {
		try {
			if (session.getAttribute("loginUser") != null) {
				// 회원 정보
				User user = (User) session.getAttribute("loginUser");
				// 주문 정보
				List<AssetOrdersResponse.Response> orderList = orderService
						.selectAssetOrderListForBuyer(user.getUserId(), Integer.parseInt(pageNo));

				// 리뷰 작성 여부
				if (orderList != null && orderList.size() > 0) {
					for (AssetOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countAssetReviewByOrderIdAndWriterId(order.getOrderId(),
								((User) session.getAttribute("loginUser")).getUserId());
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
				category4 += " 00:00:00";
				category5 += " 00:00:00";
				List<AssetOrdersResponse.Response> orderList = orderService.selectAssetOrderListForBuyer(
						user.getUserId(), category1, category2,
						LocalDateTime.parse(category4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(category5, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						Integer.parseInt(pageNo));

				// 리뷰 작성 여부
				if (orderList != null && orderList.size() > 0) {
					for (AssetOrdersResponse.Response order : orderList) {
						Long reviewCheck = reviewService.countAssetReviewByOrderIdAndWriterId(order.getOrderId(),
								((User) session.getAttribute("loginUser")).getUserId());
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
				List<AssetOrdersResponse.Response> orderList = orderService
						.selectAssetOrderListForSeller(user.getUserId(), Integer.parseInt(pageNo));

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
				category4 += " 00:00:00";
				category5 += " 00:00:00";
				List<AssetOrdersResponse.Response> orderList = orderService.selectAssetOrderListForSeller(
						user.getUserId(), category1, category2,
						LocalDateTime.parse(category4, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(category5, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						Integer.parseInt(pageNo));

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
		// 내역 생성
		int reseult = orderService.orderP(projectOrder, options, loginUser, consumeAmount);
		return reseult;
	}
}
