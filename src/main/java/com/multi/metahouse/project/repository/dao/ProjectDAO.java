package com.multi.metahouse.project.repository.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;

@Repository
public interface ProjectDAO {
	//프로젝트 상세 이미지 파일 저장하는 메서드
	int insertFile(List<ProjectContentsDTO> detailImageList);
}	
