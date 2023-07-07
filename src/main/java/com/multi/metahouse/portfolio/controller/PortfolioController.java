package com.multi.metahouse.portfolio.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriUtils;


import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioAttachFileDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioInfoDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioStyleImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioUpdateDTO;
import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.portfolio.service.MainImgUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioAttachFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjContentFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjPointFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjStyleFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioService;
import com.multi.metahouse.user.service.UserService;

@Controller
public class PortfolioController {
	PortfolioService service;
	UserService userService;
	MainImgUploadLogicService mainImgUploadService;
	PortfolioAttachFileUploadLogicService attachFileUploadService;
	PortfolioPjContentFileUploadLogicService pjContentFileUploadService;
	PortfolioPjPointFileUploadLogicService pjPointFileUploadService;
	PortfolioPjStyleFileUploadLogicService pjStyleFileUploadService;
	ResourceLoader resourceLoader;
	
	@Autowired
	public PortfolioController(PortfolioService service, UserService userService,
			MainImgUploadLogicService mainImgUploadService,
			PortfolioAttachFileUploadLogicService attachFileUploadService,
			PortfolioPjContentFileUploadLogicService pjContentFileUploadService,
			PortfolioPjPointFileUploadLogicService pjPointFileUploadService,
			PortfolioPjStyleFileUploadLogicService pjStyleFileUploadService, ResourceLoader resourceLoader) {
		super();
		this.service = service;
		this.userService = userService;
		this.mainImgUploadService = mainImgUploadService;
		this.attachFileUploadService = attachFileUploadService;
		this.pjContentFileUploadService = pjContentFileUploadService;
		this.pjPointFileUploadService = pjPointFileUploadService;
		this.pjStyleFileUploadService = pjStyleFileUploadService;
		this.resourceLoader = resourceLoader;
	}
	

	@GetMapping("mypage/portfolio")
	public ModelAndView portfolio(String portfolioId, HttpSession session) {
		ModelAndView portfolioDetail = new ModelAndView("portfolio/portfolio_detail");
		
		PortfolioInfoDTO portfolioInfo = service.read(portfolioId);
		portfolioDetail.addObject("portfolioInfo", portfolioInfo);
		
		String userId = ((User)session.getAttribute("loginUser")).getUserId();
		PortfolioDTO dto = new PortfolioDTO();
		dto.setPortfolio_id(portfolioId);
		dto.setUser_id(userId);
		List<PortfolioDTO> anotherportfolioList = service.selectPortfolioList(dto);
		System.out.println(anotherportfolioList);
		portfolioDetail.addObject("anotherportfolioList", anotherportfolioList);
		return portfolioDetail;
	}

	@GetMapping("user/portfolio")
	public ModelAndView userPortfolio(String userId, String portfolioId) {
		ModelAndView portfolioDetail = new ModelAndView("portfolio/portfolio_detail_other");
		
		User userInfo = userService.readUserInfo(userId);
		System.out.println("userInfo:"+userInfo);
		portfolioDetail.addObject("userInfo", userInfo);
		
		PortfolioInfoDTO portfolioInfo = service.read(portfolioId);
		portfolioDetail.addObject("portfolioInfo", portfolioInfo);
		
		PortfolioDTO dto = new PortfolioDTO();
		dto.setPortfolio_id(portfolioId);
		dto.setUser_id(userId);
		List<PortfolioDTO> anotherportfolioList = service.selectPortfolioList(dto);
		//System.out.println(anotherportfolioList);
		portfolioDetail.addObject("anotherportfolioList", anotherportfolioList);
		return portfolioDetail;
	}
	
	@GetMapping("mypage/create_portfolio")
	public String createPortfolio() {
		return "portfolio/create_portfolio";
	}
	
	@PostMapping(value="mypage/create_portfolio", produces="application/contentType; charset=utf-8")
	@ResponseBody
	public String createPortfolio(PortfolioDTO portfoliodto) throws IOException {
		
		//main_img 저장
		MultipartFile multipartMainImg = portfoliodto.getMultipartMainImg();
		String mainImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/mainImg").getFile().getAbsolutePath();
		String mainImgStoreFilename = mainImgUploadService.uploadFile(multipartMainImg, mainImgPath);
		portfoliodto.setMain_img_store_filename(mainImgStoreFilename);
		
		//content_img 저장
		List<PortfolioContentImgDTO> portfolioContentDtoList = null;
		if(portfoliodto.getPortfolioPjContentImg() != null) {
			List<MultipartFile> portfolioPjContentImg = portfoliodto.getPortfolioPjContentImg();
			String contentImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/contentImg").getFile().getAbsolutePath();
			portfolioContentDtoList = pjContentFileUploadService.uploadFiles(portfolioPjContentImg, contentImgPath);
		}
		
		//point_img 저장
		List<PortfolioPointImgDTO> portfolioPointDtoList = null;
		if(portfoliodto.getPortfolioPjPointImg() != null) {
			List<MultipartFile> portfolioPjPointImg = portfoliodto.getPortfolioPjPointImg();
			String pointImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/pointImg").getFile().getAbsolutePath();
			portfolioPointDtoList = pjPointFileUploadService.uploadFiles(portfolioPjPointImg, pointImgPath);
		}
		
		//style_img 저장
		List<PortfolioStyleImgDTO> portfolioStyleDtoList = null;
		if(portfoliodto.getPortfolioPjStyleImg() != null) {
			List<MultipartFile> portfolioPjStyleImg = portfoliodto.getPortfolioPjStyleImg();
			String styleImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/styleImg").getFile().getAbsolutePath();
			portfolioStyleDtoList = pjStyleFileUploadService.uploadFiles(portfolioPjStyleImg, styleImgPath);
		}
		
		//attach_file 저장
		List<PortfolioAttachFileDTO> portfolioAttachFileDtoList = null;
		if(portfoliodto.getPortfolioAttachFile() != null) {
			List<MultipartFile> portfolioAttachFile = portfoliodto.getPortfolioAttachFile();
			String attachFilePath = resourceLoader.getResource("classpath:static/upload/portfolio/attachFile").getFile().getAbsolutePath();
			portfolioAttachFileDtoList = attachFileUploadService.uploadFiles(portfolioAttachFile, attachFilePath);
		}
		
		
		service.insert(portfoliodto, portfolioContentDtoList, portfolioPointDtoList, portfolioStyleDtoList, portfolioAttachFileDtoList);
		
		return "profile";
	}
	
	@GetMapping("mypage/update_portfolio")
	public ModelAndView updatePortfolio(String portfolioId) {
		System.out.println(portfolioId);
		ModelAndView portfolioUpdate = new ModelAndView("portfolio/update_portfolio");
		PortfolioInfoDTO portfolioInfo = service.read(portfolioId);
		portfolioUpdate.addObject("portfolioInfo", portfolioInfo);
		
		return portfolioUpdate;
	}
	
	@PostMapping(value="mypage/update_portfolio",  produces="application/contentType; charset=utf-8")
	@ResponseBody
	public String updatePortfolio(PortfolioDTO portfoliodto) throws IOException{
		System.out.println(portfoliodto);
		
		if(portfoliodto.getMultipartMainImg() != null) {
			MultipartFile multipartMainImg = portfoliodto.getMultipartMainImg();
			String mainImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/mainImg").getFile().getAbsolutePath();
			String mainImgStoreFilename = mainImgUploadService.uploadFile(multipartMainImg, mainImgPath);
			portfoliodto.setMain_img_store_filename(mainImgStoreFilename);
		}

		//content_img Update
		List<PortfolioContentImg> portfolioContentList = null;
		if(portfoliodto.getPortfolioPjContentImg() != null) {
			List<MultipartFile> portfolioPjContentImg = portfoliodto.getPortfolioPjContentImg();
			String contentImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/contentImg").getFile().getAbsolutePath();
			portfolioContentList = pjContentFileUploadService.entityUploadFiles(portfolioPjContentImg, contentImgPath);
		}
		
		//point_img Update
		List<PortfolioPointImg> portfolioPointList = null;
		if(portfoliodto.getPortfolioPjPointImg() != null) {
			List<MultipartFile> portfolioPjPointImg = portfoliodto.getPortfolioPjPointImg();
			String pointImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/pointImg").getFile().getAbsolutePath();
			portfolioPointList = pjPointFileUploadService.entityUploadFiles(portfolioPjPointImg, pointImgPath);
		}
		
		//style_img Update
		List<PortfolioStyleImg> portfolioStyleList = null;
		if(portfoliodto.getPortfolioPjStyleImg() != null) {
			List<MultipartFile> portfolioPjStyleImg = portfoliodto.getPortfolioPjStyleImg();
			String styleImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/styleImg").getFile().getAbsolutePath();
			portfolioStyleList = pjStyleFileUploadService.entityUploadFiles(portfolioPjStyleImg, styleImgPath);
		}
		
		//attach_file Update
		List<PortfolioAttachFile> portfolioAttachFileList = null;
		if(portfoliodto.getPortfolioAttachFile() != null) {
			List<MultipartFile> portfolioAttachFile = portfoliodto.getPortfolioAttachFile();
			String attachFilePath = resourceLoader.getResource("classpath:static/upload/portfolio/attachFile").getFile().getAbsolutePath();
			portfolioAttachFileList = attachFileUploadService.entityUploadFiles(portfolioAttachFile, attachFilePath);
		}
		
		
		PortfolioUpdateDTO portfolioUpdateDTO = new PortfolioUpdateDTO(portfoliodto, portfolioContentList, portfolioPointList, 
				portfolioStyleList, portfolioAttachFileList);
		
		System.out.println(portfolioUpdateDTO);
		service.update(portfolioUpdateDTO);
		
		return "portfolio?portfolioId="+portfoliodto.getPortfolio_id();
	}
	
	@RequestMapping("mypage/delete_portfolio")
	public String deletePortfolio(String portfolioId) {
		service.delete(portfolioId);
		return "redirect:profile";
	}
	
	//다운로드
	@RequestMapping("/portfolio-attachfile/download/{attachFileId}/{portfolioId}/{attachFileno}")
	public ResponseEntity<UrlResource> downloadFile(@PathVariable String attachFileId, @PathVariable String portfolioId, 
										@PathVariable String attachFileno) throws IOException {
		
		System.out.println(attachFileId+","+portfolioId+","+attachFileno);
		
		PortfolioAttachFile selectfileInfo =service.getFile(new PortfolioAttachFile(attachFileId, portfolioId, "", "", attachFileno));
		System.out.println(selectfileInfo);
		String attachFilePath = resourceLoader.getResource("classpath:static/upload/portfolio/attachFile").getFile().getAbsolutePath();
		System.out.println(attachFilePath);
		
		UrlResource resource = new UrlResource("file:"+ attachFilePath + File.separator + selectfileInfo.getAttachStoreFilename());
		
		//3. 파일명에 한글이 있는 경우 오류가 발생하지 않도록 처리 - 다운로드되는 파일명
		//UUID로 다운도 되지만, 원본 파일의 이름이 다운로드 되도록 만들 것
		String encodedFilename = UriUtils.encode(selectfileInfo.getAttachFilename(), "UTF-8");
		
		//4. 파일을 다운로드 형식으로 응답하기 위해서 응답헤더에 셋팅
		// \"문자열로 인식시키기 위해 사용
		// => 해석 : "attachment; filename= "a.jpg"
		String mycontenttype = "attachment; filename=\"" + encodedFilename + "\"";
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, mycontenttype).body(resource);
	}
}
