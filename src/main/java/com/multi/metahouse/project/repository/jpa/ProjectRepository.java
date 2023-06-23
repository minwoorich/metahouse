package com.multi.metahouse.project.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{
	
}
