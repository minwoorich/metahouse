package com.multi.metahouse.project.service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.project.repository.dao.ProjectDAO;
import com.multi.metahouse.project.repository.jpa.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	ProjectRepository projectRepo;
	ProjectDAO projectDao;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepo, ProjectDAO projectDao) {
		super();
		this.projectRepo = projectRepo;
		this.projectDao = projectDao;
	}

	@Override
//	@Transactional
	public void insertProjectInfo(ProjectEntity projectEntity) {
		projectRepo.save(projectEntity);
	}
}
