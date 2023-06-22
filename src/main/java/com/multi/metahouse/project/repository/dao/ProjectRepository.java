package com.multi.metahouse.project.repository.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{
	public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc();
}