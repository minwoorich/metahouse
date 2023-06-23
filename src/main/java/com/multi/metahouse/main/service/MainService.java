package com.multi.metahouse.main.service;

import java.util.List;

import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.review.UnionReviewDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface MainService {
	//public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc();
	
	public List<ProjectDTO> test();
	public List<AssetDTO> findTopNByAssetReviewAvg(int limit);
	public List<ProjectDTO> findTopNByProjectReviewAvgWithPrice(int limit);
	
	public List<UnionReviewDTO> findOrderByDate(int limit);
}
