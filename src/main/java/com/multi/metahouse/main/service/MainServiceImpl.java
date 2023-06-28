package com.multi.metahouse.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.project.ProjectReviewDTO;
import com.multi.metahouse.domain.dto.review.UnionReviewDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.project.repository.dao.ProjectDAO;
import com.multi.metahouse.review.repository.dao.ReviewDAO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
@Transactional
public class MainServiceImpl implements MainService{
	
	private AssetDAO assetDAO;
	private ProjectDAO projectDAO;
	private ReviewDAO reviewDAO;
	
	@Autowired
	public MainServiceImpl(AssetDAO assetDAO, ProjectDAO projectDAO,ReviewDAO reviewDAO) {
		this.assetDAO = assetDAO;
		this.projectDAO = projectDAO;
		this.reviewDAO = reviewDAO;
	}


	@Override
	public List<ProjectReviewDTO>test() {
		return projectDAO.test();
	}

	@Override
	public List<AssetDTO> findTopNByAssetReviewAvg(int limit) {
		return assetDAO.findTopNByAssetReviewAvg(limit);
	}

	@Override
	public List<ProjectReviewDTO>findTopNByProjectReviewAvgWithPrice(int limit) {
		
		return projectDAO.findTopNByProjectReviewAvg(limit);
	}

	@Override
	public List<UnionReviewDTO> findOrderByDate(int limit) {
		return reviewDAO.findOrderByDate(limit);
	}

	
	
	
}
