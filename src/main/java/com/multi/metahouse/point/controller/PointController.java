package com.multi.metahouse.point.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
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
		
		System.out.println("myPoint : " + myPoint);
		
		mav.addObject("cgpilist", myPoint.getChargedInfoList());
		mav.addObject("cspilist", myPoint.getConsumedInfoList());
		mav.addObject("tcgp", myPoint.getTotalChargedPoint());
		mav.addObject("tcsp", myPoint.getTotalConsumedPoint());
		mav.setViewName("point/point");
		return mav;
	}
	
	@GetMapping("/charge")
	public String viewPointChargePage() {
		return "point/charge";
	}
	
	@GetMapping("/charge.do")
	public String pointCharge(HttpSession session, int point) {
		User loginUser = (User)session.getAttribute("loginUser");
		
		System.out.println("point = " + point);
		System.out.println("loginUser = " + loginUser);
		
		service.chargePoint(loginUser, point);
		
		return "point/charge";
	}
}
