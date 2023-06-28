package com.multi.metahouse.project.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectDAO {
	/* ----------------------민우 영역---------------------------- */
	void insert(ProjectEntity projectEntity);
	
	List<ProjectEntity> selectAllProjects();
	
	List<ProjectEntity> selectListByUserId(String userId);
	
	void delete(Long project_id);
	
	/* -------------------------------------------------------- */

	public List<ProjectDTO> test();

	public List<ProjectDTO> findTopNByProjectReviewAvg(int limit);
	
/*------------------------------- OSE -----------------------------*/
	public ProjectDTO projectInfo(Long project_id);
	public List<ProjectContentsDTO> projectImg(Long project_id);
	public List<ProjectAddOption> projectOption(Long project_id);
}
