package com.multi.metahouse.cash;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class cashcontroller {
	@RequestMapping("cash/mycash")
	public String cash() {
		return "cash/cash";
	}

}

