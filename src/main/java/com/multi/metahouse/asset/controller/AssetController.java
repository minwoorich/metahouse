package com.multi.metahouse.asset.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.asset.service.AssetFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.dto.asset.AssetFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.project.ProjectPageDTO;
import com.multi.metahouse.domain.dto.review.AssetReviewDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.review.service.ReviewService;

@Controller
public class AssetController {
	AssetService service;
	AssetFileUploadLogicService assetfileuploadservice;
	AttachFileUploadLogicService attachfileuploadservice;
	ResourceLoader resourceLoader;
	ReviewService reviewService;

	@Autowired
	public AssetController(AssetService service, AssetFileUploadLogicService assetfileuploadservice,
			AttachFileUploadLogicService attachfileuploadservice, ResourceLoader resourceLoader,
			ReviewService reviewService) {
		super();
		this.service = service;
		this.assetfileuploadservice = assetfileuploadservice;
		this.attachfileuploadservice = attachfileuploadservice;
		this.resourceLoader = resourceLoader;
		this.reviewService = reviewService;
	}

	// 에셋 설명 입력하는 페이지 반환
	@GetMapping("asset/forms/descriptions")
	public String writeForm(HttpSession session) {
		return "asset/asset_assetform01";
	}

	@GetMapping("asset/forms/preview")
	public String getFormPreview(HttpSession session, Model model) {
		System.out.println("[세션]assetForm = " + session.getAttribute("assetForm"));

		return "asset/asset_assetform02";
	}

	@RequestMapping(value = "asset/forms-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(HttpSession session, AssetFormDTO assetForm) {
		// 세션 저장
		session.setAttribute("assetForm", assetForm);
		System.out.println("[두번째 페이지 호출]assetForm = " + assetForm);
		return "/metahaus/asset/forms/preview";
	}

	@GetMapping("asset/forms")
	public String insertForm(HttpSession session) throws IOException {
		String attachFileUploadPath = resourceLoader.getResource("classpath:static/upload/asset_attach_file").getFile()
				.getAbsolutePath();
		String thumbnNailFileUploadPath = resourceLoader.getResource("classpath:static/upload/asset_thumbnail_img")
				.getFile().getAbsolutePath();
		String optioinalFileUploadPath = resourceLoader.getResource("classpath:static/upload/asset_optional_img")
				.getFile().getAbsolutePath();
		AssetFormDTO assetFormDto = new AssetFormDTO();
		// 세션에서 DTO 데이터 추출 및 미사용 세션 삭제
		assetFormDto = (AssetFormDTO) session.getAttribute("assetForm");

		if (session.getAttribute("assetForm") != null) {
			session.removeAttribute("assetForm");
		}

		// 파일업로드
		String storeAttachFileName = attachfileuploadservice.uploadFile(assetFormDto.getAttach_file(),
				attachFileUploadPath);
		System.out.println("'에셋 컨트롤러' 파일 이름 : " + storeAttachFileName);
		String storeThumbnailFileName = assetfileuploadservice.uploadFile(assetFormDto.getThumbnail(),
				thumbnNailFileUploadPath);
		List<AssetDetailImgDTO> storeOptionalFileNameList = assetfileuploadservice
				.uploadFiles(assetFormDto.getDetailImages(), optioinalFileUploadPath);

		// 서비스 호출 (insert 작업)
		service.insert(storeAttachFileName, storeThumbnailFileName, storeOptionalFileNameList, assetFormDto);
		// List<MultipartFile> imageList = session.getAttribute("imageList");
		return "redirect:/asset/my-products";
	}

	@GetMapping("asset/my-products")
	public String assetProductList(HttpSession session, Model model, String pageNo) {
		if (session.getAttribute("loginUser") != null) {
			User user = (User) session.getAttribute("loginUser");

			List<AssetDTO> assetList = service.selectAssetListBySellerId(user.getUserId(), Integer.parseInt(pageNo));
			model.addAttribute("assetList", assetList);

			return "asset/asset_product_list";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("asset/delete-product")
	public String deleteProduct(String asset_id) {
		service.deleteAssetByAssetId(asset_id);

		return "redirect:/asset/my-products";
	}
	
/*---------------------------------- OSE ---------------------------------------------*/
//	에셋마켓 전체 상품리스트 보기> 나중에 정렬 기준값도 받아와야하고(최신 등록순이 디폴트값 줄예정), 카테고리값도 받아와야됨
//	 +리뷰 정보도,,,
	@RequestMapping("asset/main")
	public String assetMarket(Model model, @RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "Non") String category1,
			@RequestParam(defaultValue = "Non") String category2) {
		
		System.out.println(pageNo + category1 + category2);
		List<AssetDTO> assetlist = service.list(pageNo, category1, category2);
		int total = service.list(null, category1, category2).size();

		model.addAttribute("assetlist", assetlist);
		model.addAttribute("pageInfo", new ProjectPageDTO(total, pageNo, 16, 5));
		
		return "asset/main";
	}

//	특정 에셋상품보기
	@RequestMapping("asset/detail")
	public String showAsset(Model model, String assetNum, HttpSession session) {
		AssetDTO asset = service.assetInfo(assetNum);
		List<AssetDetailImgDTO> assetImgs = service.assetImgInfo(assetNum);
		List<AssetContentDTO> assetContents = service.assetContentInfo(assetNum);
		List<AssetReviewDTO> assetReviews = reviewService.getAllReviewsByAid(assetNum);
		User userInfo = (User) session.getAttribute("loginUser");
		model.addAttribute("asset", asset);
		model.addAttribute("assetImgs", assetImgs);
		model.addAttribute("assetContents", assetContents);
		model.addAttribute("assetReviews", assetReviews);
		return "asset/market_detail";
	}

//	구매하기 페이지 보여주기
	@GetMapping("asset/purchase")
	public String puchaseAsset(Model model, String assetNum, HttpSession session) {
		AssetDTO asset = service.assetInfo(assetNum);
		User userInfo = (User) session.getAttribute("loginUser");

		model.addAttribute("asset", asset);
		model.addAttribute("userInfo", userInfo);

		return "order/asset_purchase";
	}

}