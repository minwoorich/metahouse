package com.multi.metahouse.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MarketController {
	@RequestMapping("assetMarket/main")
	public String assetMarket() {
		return "market/Market_main_asset";
	}
	@RequestMapping("assetMarket/detail")
	public String showAsset() {
		return "market/product_detail_asset";
	}
	@RequestMapping("gigsSell/main")
	public String gigs1() {
		return "market/Market_main_gigs_1";
	}
	@RequestMapping("gigsSell/detail")
	public String showGigs1() {
		return "market/product_detail_gigs_1";
	}
	@RequestMapping("gigsGather/main")
	public String gigs2() {
		return "market/Market_main_gigs_2";
	}
	@RequestMapping("gigsGather/detail")
	public String showGigs2() {
		return "market/product_detail_gigs_2";
	}
	@GetMapping("product/purchase")
	public String otherProfile() {
		return "market/purchase";
	}
}
