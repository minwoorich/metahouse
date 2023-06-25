package com.multi.metahouse.project.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectDAO {
	/* ----------------------민우 영역---------------------------- */
	void insert(ProjectEntity projectEntity);
	
	
	
	/* -------------------------------------------------------- */

	public List<ProjectDTO> test();

	public List<ProjectDTO> findTopNByProjectReviewAvg(int limit);
	
	
	
}
