package com.multi.metahouse.portfolio.controller;

import java.io.IOException;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.multi.metahouse.asset.service.AttachFileUploadLogicService;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioAttachFileDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioInfoDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioStyleImgDTO;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.portfolio.service.MainImgUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioAttachFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjContentFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjPointFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioPjStyleFileUploadLogicService;
import com.multi.metahouse.portfolio.service.PortfolioService;

@Controller
public class PortfolioController {
	PortfolioService service;
	MainImgUploadLogicService mainImgUploadService;
	PortfolioAttachFileUploadLogicService attachFileUploadService;
	PortfolioPjContentFileUploadLogicService pjContentFileUploadService;
	PortfolioPjPointFileUploadLogicService pjPointFileUploadService;
	PortfolioPjStyleFileUploadLogicService pjStyleFileUploadService;
	ResourceLoader resourceLoader;
	
	@Autowired
	public PortfolioController(PortfolioService service, MainImgUploadLogicService mainImgUploadService,
			PortfolioAttachFileUploadLogicService attachFileUploadService,
			PortfolioPjContentFileUploadLogicService pjContentFileUploadService,
			PortfolioPjPointFileUploadLogicService pjPointFileUploadService,
			PortfolioPjStyleFileUploadLogicService pjStyleFileUploadService, ResourceLoader resourceLoader) {
		super();
		this.service = service;
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
		List<MultipartFile> portfolioPjContentImg = portfoliodto.getPortfolioPjContentImg();
		String contentImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/contentImg").getFile().getAbsolutePath();
		List<PortfolioContentImgDTO> portfolioContentDtoList = pjContentFileUploadService.uploadFiles(portfolioPjContentImg, contentImgPath);
		
		//point_img 저장
		List<MultipartFile> portfolioPjPointImg = portfoliodto.getPortfolioPjPointImg();
		String pointImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/pointImg").getFile().getAbsolutePath();
		List<PortfolioPointImgDTO> portfolioPointDtoList = pjPointFileUploadService.uploadFiles(portfolioPjPointImg, pointImgPath);
		
		//style_img 저장
		List<MultipartFile> portfolioPjStyleImg = portfoliodto.getPortfolioPjStyleImg();
		String styleImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/styleImg").getFile().getAbsolutePath();
		List<PortfolioStyleImgDTO> portfolioStyleDtoList = pjStyleFileUploadService.uploadFiles(portfolioPjStyleImg, styleImgPath);
		
		//attach_file 저장
		List<MultipartFile> portfolioAttachFile = portfoliodto.getPortfolioAttachFile();
		String attachFilePath = resourceLoader.getResource("classpath:static/upload/portfolio/attachFile").getFile().getAbsolutePath();
		List<PortfolioAttachFileDTO> portfolioAttachFileDtoList = attachFileUploadService.uploadFiles(portfolioAttachFile, attachFilePath);
		
		
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
		
		String[] delContentImgArr = portfoliodto.getDelContentImg().split(",");
		String[] delPointImgArr = portfoliodto.getDelPointImg().split(",");
		String[] delStyleImgArr = portfoliodto.getDelStyleImg().split(",");
		
		int contentSize = portfoliodto.getContentSize();
		int pointSize = portfoliodto.getPointSize();
		int styleSize = portfoliodto.getStyleSize();
		
		//content_img Update
		List<PortfolioContentImgDTO> portfolioContentDtoList = null;
		if(portfoliodto.getPortfolioPjContentImg() != null) {
			if(delContentImgArr.length != 0) {
				List<MultipartFile> portfolioPjContentImg = portfoliodto.getPortfolioPjContentImg();
				String contentImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/contentImg").getFile().getAbsolutePath();
				portfolioContentDtoList = pjContentFileUploadService.uploadFiles(portfolioPjContentImg, contentImgPath, delContentImgArr);
			}else {
				List<MultipartFile> portfolioPjContentImg = portfoliodto.getPortfolioPjContentImg();
				String contentImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/contentImg").getFile().getAbsolutePath();
				portfolioContentDtoList = pjContentFileUploadService.uploadFiles(portfolioPjContentImg, contentImgPath, contentSize);
			}
		}
		System.out.println(portfolioContentDtoList);
		
		//point_img Update
		List<PortfolioPointImgDTO> portfolioPointDtoList = null;
		if(portfoliodto.getPortfolioPjPointImg() != null) {
			if(delPointImgArr.length != 0) {
				List<MultipartFile> portfolioPjPointImg = portfoliodto.getPortfolioPjPointImg();
				String pointImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/pointImg").getFile().getAbsolutePath();
				portfolioPointDtoList = pjPointFileUploadService.uploadFiles(portfolioPjPointImg, pointImgPath, delPointImgArr);
			}
		}
		System.out.println(portfolioPointDtoList);
		
		//style_img Update
		List<PortfolioStyleImgDTO> portfolioStyleDtoList = null;
		if(portfoliodto.getPortfolioPjStyleImg() != null) {
			if(delStyleImgArr.length != 0) {
				List<MultipartFile> portfolioPjStyleImg = portfoliodto.getPortfolioPjStyleImg();
				String styleImgPath = resourceLoader.getResource("classpath:static/upload/portfolio/styleImg").getFile().getAbsolutePath();
				portfolioStyleDtoList = pjStyleFileUploadService.uploadFiles(portfolioPjStyleImg, styleImgPath, delStyleImgArr);
			}
		}
		System.out.println(portfolioStyleDtoList);
		
		//attach_file Update
		List<PortfolioAttachFileDTO> portfolioAttachFileDtoList = null;
		if(portfoliodto.getPortfolioAttachFile() != null) {
			List<MultipartFile> portfolioAttachFile = portfoliodto.getPortfolioAttachFile();
			String attachFilePath = resourceLoader.getResource("classpath:static/upload/portfolio/attachFile").getFile().getAbsolutePath();
			portfolioAttachFileDtoList = attachFileUploadService.uploadFiles(portfolioAttachFile, attachFilePath);
		}
		System.out.println(portfolioAttachFileDtoList);
		
		return "redirect:main/index";
	}
	
	@RequestMapping("mypage/delete_portfolio")
	public String deletePortfolio(String portfolioId) {
		service.delete(portfolioId);
		return "redirect:profile";
	}
}
