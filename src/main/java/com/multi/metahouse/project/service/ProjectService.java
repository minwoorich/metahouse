package com.multi.metahouse.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectPackageForm;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectService {
	void insertProjectInfo(ProjectFormDTO projectFormDto, 
			ProjectPackageForm packageFormDto,
			String thumbnailPath, 
			List<ProjectContentsDTO> contentsList);

	
/*-------------------------------------------------------------------------------------*/
	public Page<ProjectEntity> list(String category1, String category2, int pageNo);

}
