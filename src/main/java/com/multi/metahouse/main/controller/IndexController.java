package com.multi.metahouse.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.review.UnionReviewDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.main.service.MainService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Controller
public class IndexController {
	
	MainService service;
	
	
	@Autowired
	public IndexController(MainService service) {
		this.service = service;
	}
	
	@RequestMapping("main/index")
	public String index(Model model) {
		
		List<AssetDTO> assetList = service.findTopNByAssetReviewAvg(9);
		List<ProjectDTO> projectList = service.findTopNByProjectReviewAvgWithPrice(9);
		List<UnionReviewDTO> reviewList = service.findOrderByDate(9);
		
		reviewList.forEach((r)->{
			System.out.println(r);
		});
		
		model.addAttribute("projectList",projectList);
		model.addAttribute("assetList",assetList);
		model.addAttribute("reviewList",reviewList);
		
		return "main/index";
	}

}
