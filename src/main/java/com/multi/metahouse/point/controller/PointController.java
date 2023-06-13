package com.multi.metahouse.point.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PointController {
	@RequestMapping("cash/mycash")
	public String cash() {
		return "cash/cash";
	}

}

