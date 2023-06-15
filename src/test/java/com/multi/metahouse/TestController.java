package com.multi.metahouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	TestService service;

	@Autowired
	public TestController(TestService service) {
		super();
		this.service = service;
	}
	
	@RequestMapping("/filetest")
	public String fileupload() {
		
		return "project/projectform01";
	}
	
}
