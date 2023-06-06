package com.multi.metahouse.asset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asset")
public class AssetController {
	@RequestMapping("/saleslist")
	public String assetSale() {
		return "market_sm/asset/asset_saleslist";
	}
}
