package com.multi.metahouse.main.service;

import java.util.List;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface MainService {
	public List<AssetEntity> findTop9ByOrderByAssetHitsDesc();
	public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc();
	
	public List<ProjectDTO> test();
}
