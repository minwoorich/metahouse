package com.multi.metahouse.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AssetMarketController {
	@RequestMapping("asset/main")
	public String assetMarket() {
		return "asset/main";
	}

	@RequestMapping("asset/detail")
	public String showAsset() {
		return "asset/market_detail";
	}

	@GetMapping("product/purchase_asset")
	public String puchaseAsset() {
		return "order/asset_purchase";
	}
}
