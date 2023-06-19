package com.multi.metahouse.asset.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.multi.metahouse.asset.service.AssetService;
import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.asset.service.AssetFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;

@Controller
@RequestMapping("/asset")
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
	
	@GetMapping("/asset-form-01")
	public String assetForm01() {
		return "asset/asset_assetform01";
	}
	


	@PostMapping("/asset-form-01")
	public String assetForm01(AssetDTO dto, HttpSession session) throws IllegalStateException, IOException {
		List<MultipartFile> attach_file = dto.getAttach_file();
		String attach_filePath = resourceLoader.getResource("classpath:static/upload/asset_attach_file").getFile().getAbsolutePath();
		
		List<AssetContentDTO> attachfiledtolist = attachfileuploadservice.uploadFiles(attach_file, attach_filePath);
		
		MultipartFile thumbnail_img = dto.getThumbnail_img();
		String thumbnail_imgPath = resourceLoader.getResource("classpath:static/upload/asset_thumbnail_img").getFile().getAbsolutePath();
		String thumbnailfiledtolist = assetfileuploadservice.uploadFile(thumbnail_img, thumbnail_imgPath);
		dto.setMain_img(thumbnailfiledtolist);
		
		List<MultipartFile> optional_img = dto.getOptional_img();
		String optional_imgPath = resourceLoader.getResource("classpath:static/upload/asset_optional_img").getFile().getAbsolutePath();
		List<AssetDetailImgDTO> optionalfiledtolist = assetfileuploadservice.uploadFiles(optional_img, optional_imgPath);
		
		System.out.println(dto);
		System.out.println("=========================================");
		System.out.println(attach_file);
		System.out.println(attach_filePath);
		System.out.println("=========================================");
		System.out.println(thumbnail_img);
		System.out.println(thumbnail_imgPath);
		System.out.println("=========================================");
		System.out.println(optional_img);
		System.out.println(optional_imgPath);
		System.out.println(optionalfiledtolist);
		
		service.insert(dto, optionalfiledtolist, attachfiledtolist);
		return "asset/asset_assetform02";
	}
	
	@GetMapping("/asset-form-02")
	public String assetForm02() throws IOException {
		String path = resourceLoader.getResource("classpath:static/upload").getFile().getAbsolutePath();
		//String path = new ClassPathResource("static/upload").getFile().getAbsolutePath();
		System.out.println(path);
		return "asset/asset_assetform02";
	}
	
	@GetMapping("/asset-product")
	public String assetFormlist() {		
		return "asset/asset_product_list";
	}
	
}