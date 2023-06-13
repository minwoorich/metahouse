package com.multi.metahouse.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexcontroller {
	@RequestMapping("main/index")
	public String index() {
		return "thymeleaf/member/index";
	}

}
