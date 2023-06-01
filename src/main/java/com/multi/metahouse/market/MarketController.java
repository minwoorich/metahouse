package com.multi.metahouse.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketController {
	@RequestMapping("assetMarket/main")
	public String assetMarket() {
		return "assetMarket/main";
	}
	@RequestMapping("assetMarket/detail")
	public String showAsset() {
		return "assetMarket/detail";
	}
	@RequestMapping("gigsSell/main")
	public String gigs1() {
		return "gigsSell/main";
	}
	@RequestMapping("gigsSell/detail")
	public String showGigs1() {
		return "gigsSell/detail";
	}
	@RequestMapping("gigsGather/main")
	public String gigs2() {
		return "gigsGather/main";
	}
	@RequestMapping("gigsGather/detail")
	public String showGigs2() {
		return "gigsGather/detail";
	}
}
