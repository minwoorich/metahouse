package com.multi.metahouse.project.service;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectService {
	int insertProjectInfo();
	
/*-------------------------------------------------------------------------------------*/
	public Page<ProjectEntity> list(String category1, String category2, int pageNo);
}
