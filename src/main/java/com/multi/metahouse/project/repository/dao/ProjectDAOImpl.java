package com.multi.metahouse.project.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.project.repository.jpa.ProjectRepository;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	SqlSession session;
	ProjectRepository projectRepo;
	
	@Autowired
	public ProjectDAOImpl(SqlSession session, ProjectRepository projectRepo) {
		super();
		this.session = session;
		this.projectRepo = projectRepo;
	}



	@Override
	public void insert(ProjectEntity projectEntity) {
		projectRepo.save(projectEntity);
	}

}
