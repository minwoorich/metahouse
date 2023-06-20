package com.multi.metahouse.asset.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

@Controller
public class AssetMarketController {
	AssetService service;

	@Autowired
	public AssetMarketController(AssetService service) {
		super();
		this.service = service;
	}

//	에셋마켓 전체 상품리스트 보기> 나중에 정렬 기준값도 받아와야하고(최신 등록순이 디폴트값 줄예정), 카테고리값도 받아와야됨
//	 +리뷰 정보도,,,
	@RequestMapping("asset/main")
	public String assetMarket(Model model, String pageNo) {
		
		Page<AssetEntity> assetlistPage = service.list(Integer.parseInt(pageNo));
		List<AssetEntity> assetlist = assetlistPage.getContent();
		
		model.addAttribute("aPage",assetlistPage);
		model.addAttribute("assetlist", assetlist);
		model.addAttribute("maxPage", 5);
		
		return "asset/main";
	}

//	특정 에셋상품보기> 조회수 업데이트 해주기
	@RequestMapping("asset/detail")
	public String showAsset(Model model, String assetNum) {
		AssetDTO asset = service.assetInfo(assetNum);
		List<AssetDetailImgDTO> assetImgs = service.assetImgInfo(assetNum);
		List<AssetContentDTO> assetContents = service.assetContentInfo(assetNum);
		model.addAttribute("asset", asset);
		model.addAttribute("assetImgs", assetImgs);
		model.addAttribute("assetContents", assetContents);
		return "asset/market_detail";
	}

	@GetMapping("product/purchase_asset")
	public String puchaseAsset() {
		return "order/asset_purchase";
	}
}
