package com.multi.metahouse.asset.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.user.User;

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
	public String assetMarket(Model model, String pageNo, String category1, String category2) {

		Page<AssetEntity> assetlistPage = service.list(category1, category2, Integer.parseInt(pageNo));
		List<AssetEntity> assetlist = assetlistPage.getContent();

		model.addAttribute("aPage", assetlistPage);
		model.addAttribute("assetlist", assetlist);
		model.addAttribute("maxPage", 5);

		return "asset/main";
	}

//	특정 에셋상품보기
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

//	구매하기 페이지 보여주기
	@GetMapping("product/purchase_asset")
	public String puchaseAsset(Model model, String assetNum, HttpSession session) {
		AssetDTO asset = service.assetInfo(assetNum);

		model.addAttribute("asset", asset);

		String view = null;
		User userInfo = (User) session.getAttribute("loginUser");

		if (userInfo == null) {
			view = "redirect:/login";
		} else {
			model.addAttribute("userId", userInfo.getUserId());
			view = "order/asset_purchase";
		}
		return view;
	}

}
