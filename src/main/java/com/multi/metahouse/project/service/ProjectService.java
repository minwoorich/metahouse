package com.multi.metahouse.project.service;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectService {
	void insertProjectInfo(ProjectEntity projectEntity);

	
/*-------------------------------------------------------------------------------------*/
	public Page<ProjectEntity> list(String category1, String category2, int pageNo);

}
