package com.multi.metahouse.project.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.project.ProjectReviewDTO;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectDAO {
	/* ----------------------민우 영역---------------------------- */
	void insert(ProjectEntity projectEntity);
	
	List<ProjectEntity> selectAllProjects();
	
	void delete(Long project_id);
	
	/* -------------------------------------------------------- */

	public List<ProjectReviewDTO> test();

	public List<ProjectReviewDTO> findTopNByProjectReviewAvg(int limit);
	
	
	
}
