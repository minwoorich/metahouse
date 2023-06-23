package com.multi.metahouse.project.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.project.ProjectDTO;
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

	@Override 
	public List<ProjectDTO> test() {
		return session.selectList("com.multi.metahouse.project.getProjectWithPrice");
	}

	@Override
	public List<ProjectDTO> findTopNByProjectReviewAvg(int limit) {
		return session.selectList("com.multi.metahouse.project.findOrderByReviewRating",limit);
	}

//	@Override
//	public List<ProjectEntity> findTop9ByOrderByProjectHitsDesc() {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
