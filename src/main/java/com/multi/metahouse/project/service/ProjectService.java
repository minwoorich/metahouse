package com.multi.metahouse.project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectFormDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectListDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectPackageForm;

public interface ProjectService {

/*-------------------민우 영역-------------------------------------------------*/
	// 프로젝트 등록
	void insertProjectInfo(ProjectFormDTO projectFormDto, ProjectPackageForm packageFormDto, String thumbnailPath, List<ProjectContentsDTO> contentsList);
	// 전체 프로젝트 출력
	List<ProjectListDTO> selectAllProjects();
	// 유저 아이디로 프로젝트 출력
	List<ProjectListDTO> selectListByUserId(String userId, int pageNo);
	// 아이디로 프로젝트 삭제
	void deleteProject(Long projectId);

/*------------------------------------- OSE ------------------------------------------------*/
	public List<ProjectDTO> list(Integer currnetPage, String category1, String category2);
	public ProjectDTO projectInfo(Long projectNum);
	public List<ProjectContentsDTO> projectImg(Long projectNum);
	public List<ProjectAddOption> projectOption(Long project_id);

}
