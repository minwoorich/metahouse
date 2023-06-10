package com.multi.metahouse.asset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset")
public class AssetController {
	@RequestMapping("/saleslist")
	public String assetSale() {
		return "market_sm/asset/asset_saleslist";
	}
	
	@GetMapping("/asset-form-01")
	public String assetForm01() {
		return "market_sm/asset/mypage_asset_assetform01";
	}
	
	@GetMapping("/asset-form-02")
	public String assetForm02() {
		return "market_sm/asset/mypage_asset_assetform02";
	}
	
	@GetMapping("/asset-product")
	public String assetFormlist() {
		return "market_sm/asset/mypage_asset_product_list";
	}
}
