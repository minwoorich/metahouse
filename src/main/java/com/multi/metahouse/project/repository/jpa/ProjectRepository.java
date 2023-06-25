package com.multi.metahouse.project.repository.jpa;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long>{
	/* --------------------------------------민우-------------------------------------- */
	
	
	
	/* ------------------------------------창훈님--------------------------------------- */

	/*---------------------------------- OSE ---------------------------------*/
		Page<ProjectEntity> findByCategory1(@Param("category1") String category1, PageRequest pageRequest);

		Page<ProjectEntity> findByCategory1AndCategory2Pj(@Param("category1") String category1,
				@Param("category2Pj") String category2, PageRequest pageRequest);
}
