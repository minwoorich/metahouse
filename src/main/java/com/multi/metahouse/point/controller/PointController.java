package com.multi.metahouse.point.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String viewMyPointPage() {
		return "point/point";
	}
	
	@GetMapping("/charge")
	public String viewPointChargePage() {
		return "point/charge";
	}
	
	@GetMapping("/charge.do")
	public String pointCharge(HttpSession session, int point) {
		System.out.println("point = " + point);
		
		service.chargePoint(point);
		
		return "point/charge";
	}
}
