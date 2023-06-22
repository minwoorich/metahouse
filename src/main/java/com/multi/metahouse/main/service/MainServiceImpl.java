package com.multi.metahouse.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.project.repository.dao.ProjectDAO;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Service
public class MainServiceImpl implements MainService{
	
	private AssetDAO assetDAO;
	private ProjectDAO projectDAO;
	
	@Autowired
	public MainServiceImpl(AssetDAO assetDAO, ProjectDAO projectDAO) {
		this.assetDAO = assetDAO;
		this.projectDAO = projectDAO;
	}

	@Override
	public List<AssetEntity> findTop9ByOrderByAssetHitsDesc() {
		return assetDAO.findTop9ByOrderByAssetHitsDesc();
	}
	
	@Override
	public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc() {
		return projectDAO.findTop9ByOrderByProjectHitsDesc();
	}

	@Override
	public List<ProjectDTO> test() {
		return projectDAO.test();
	}

	
	
	
}
