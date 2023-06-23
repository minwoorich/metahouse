package com.multi.metahouse.project.repository.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.project.ProjectEntity;

@Repository
public class ProjectDAOImpl implements ProjectDAO{
	
	SqlSession SqlSession;
	ProjectRepository repo;
	EntityManager em;
	
	public ProjectDAOImpl() {

	}
	
	@Autowired
	public ProjectDAOImpl(ProjectRepository repo, EntityManager em, SqlSession SqlSession) {
		this.repo = repo;
		this.em = em;
		this.SqlSession = SqlSession;
	}
	
	@Override
	public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc() {
		return repo.findTop9ByOrderByProjectHitsDesc();
	}

	@Override 
	public List<ProjectDTO> test() {
		return SqlSession.selectList("com.multi.metahouse.project.getProjectWithPrice");
	}

	@Override
	public List<ProjectDTO> findTopNByProjectReviewAvg(int limit) {
		return SqlSession.selectList("com.multi.metahouse.project.findOrderByReviewRating",limit);
	}


}
