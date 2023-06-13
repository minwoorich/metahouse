package com.multi.metahouse.asset.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset")
public class AssetController {
	@GetMapping("/asset-form-01")
	public String assetForm01() {
		return "asset/asset_assetform01";
	}
	
	@GetMapping("/asset-form-02")
	public String assetForm02() {
		return "asset/asset_assetform02";
	}
	
	@GetMapping("/asset-product")
	public String assetFormlist() {
		return "asset/asset_product_list";
	}
}
