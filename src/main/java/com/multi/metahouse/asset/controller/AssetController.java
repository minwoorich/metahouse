package com.multi.metahouse.asset.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.asset.service.AssetFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.dto.asset.AssetFormDTO;
import com.multi.metahouse.domain.entity.user.User;

@Controller
public class AssetController {
	AssetService service;
	AssetFileUploadLogicService assetfileuploadservice;
	AttachFileUploadLogicService attachfileuploadservice;
	ResourceLoader resourceLoader;

	@Autowired
	public AssetController(AssetService service, AssetFileUploadLogicService assetfileuploadservice,
			AttachFileUploadLogicService attachfileuploadservice, ResourceLoader resourceLoader) {
		super();
		this.service = service;
		this.assetfileuploadservice = assetfileuploadservice;
		this.attachfileuploadservice = attachfileuploadservice;
		this.resourceLoader = resourceLoader;
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
	public String assetProductList(HttpSession session, Model model) {
		if (session.getAttribute("loginUser") != null) {
			User user = (User) session.getAttribute("loginUser");
			
			List<AssetDTO> assetList = service.selectAssetListBySellerId(user.getUserId());
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

}