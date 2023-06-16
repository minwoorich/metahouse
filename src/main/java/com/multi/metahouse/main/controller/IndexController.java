package com.multi.metahouse.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("main/index")
	public String index() {
		return "main/index";
	}

}
