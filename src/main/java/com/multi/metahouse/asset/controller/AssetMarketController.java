package com.multi.metahouse.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

@Controller
public class AssetMarketController {
	AssetService service;

	@Autowired
	public AssetMarketController(AssetService service) {
		super();
		this.service = service;
	}

	@RequestMapping("asset/main")
	public String assetMarket(Model model, String pageNo) {
		List<AssetEntity> list = service.assetlist(Integer.parseInt(pageNo));
		model.addAttribute("assetlist", list);
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
