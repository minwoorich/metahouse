package com.multi.metahouse.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
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
		
		List<AssetEntity> assetList = service.findTop9ByOrderByAssetHitsDesc();
		List<ProjectDTO> projectList = service.test();
		
		model.addAttribute("projectList",projectList);
		model.addAttribute("assetList",assetList);
		
		return "main/index";
	}

}
