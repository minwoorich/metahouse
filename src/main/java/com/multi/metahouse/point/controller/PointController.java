package com.multi.metahouse.point.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.point.service.PointService;

@Controller
@RequestMapping("/point")
public class PointController {
	
	PointService service;
	
	@Autowired
	public PointController(PointService service) {
		super();
		this.service = service;
	}

	@GetMapping("/mypoint")
	public ModelAndView viewMyPointPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		MyPointDTO myPoint = service.getMyPointDTO((User)session.getAttribute("loginUser"));
		
		mav.addObject("cgpilist", myPoint.getChargedInfoList());
		mav.addObject("cspilist", myPoint.getConsumedInfoList());
		mav.addObject("tot_cgp", myPoint.getTotalChargedPoint());
		mav.addObject("tot_csp", myPoint.getTotalConsumedPoint());
		mav.addObject("tot_page_cgpi", myPoint.getTotalPageOfChargedPointInfo());
		mav.addObject("tot_page_cspi", myPoint.getTotalPageOfConsumedPointInfo());
		
		mav.setViewName("point/point");
		return mav;
	}
	
	/* ajax 페이징 (chargedPointInfoList) */
	@PostMapping(value = "/page/cgpi", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> cgPageListJSON(HttpSession session, String pageNo) {
		Map<String, Object> json = service.chargePointInfoListJSON((User)session.getAttribute("loginUser"), Integer.parseInt(pageNo)-1);
		
		return json;
	}
	
	/* ajax 페이징 (consumedPointInfoList) */
	@PostMapping(value = "/page/cspi", produces = "application/json;charset=utf-8")
	@ResponseBody
	public Map<String, Object> csPageListJSON(HttpSession session, String pageNo) {
		Map<String, Object> json = service.consumePointInfoListJSON((User)session.getAttribute("loginUser"), Integer.parseInt(pageNo)-1);
		
		return json;
	}
	
	@GetMapping("/charge")
	public String viewPointChargePage() {
		return "point/charge";
	}
	
	@RequestMapping("/charge.do")
	@ResponseBody
	public void pointCharge(HttpSession session, int point) {
		User loginUser = (User)session.getAttribute("loginUser");
		
		service.chargePoint(loginUser, point);
		
	}

	@GetMapping("/charge.success")
	public String successCharge() {
		return "point/charge_success";
	}
}
