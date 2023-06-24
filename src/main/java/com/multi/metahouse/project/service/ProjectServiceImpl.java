package com.multi.metahouse.project.service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.project.repository.dao.ProjectDAO;
import com.multi.metahouse.project.repository.jpa.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

	ProjectRepository repository;
	ProjectDAO projectDao;
	
	@Autowired
	public ProjectServiceImpl(ProjectRepository repository, ProjectDAO projectDao) {
		super();
		this.repository = repository;
		this.projectDao = projectDao;
	}


	@Override
	@Transactional
	public void insertProjectInfo(ProjectEntity projectEntity) {
		repository.save(projectEntity);
	}

	/*-------------------------------------------------------------------------------------*/
	

	// 에셋마켓 상품리스트 출력: 카테로리 값 받아서 출력해주기
	@Override
	public Page<ProjectEntity> list(String category1, String category2, int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 16, Sort.by(Sort.Direction.DESC, "projectDate"));
		Page<ProjectEntity> projectlistPage = null;
		if (category1 == null && category2 == null) {
			projectlistPage = repository.findAll(pageRequest);
		} else if (category1 != null && category2 == null) {
			projectlistPage = repository.findByCategory1(category1, pageRequest);
		} else {
			projectlistPage = repository.findByCategory1AndCategory2Pj(category1, category2, pageRequest);
		}

		return projectlistPage;
	}
	

}
