package com.multi.metahouse.project.repository.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
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
	/* ----------------------민우 영역---------------------------- */
	@Override
	public void insert(ProjectEntity projectEntity) {
		projectRepo.save(projectEntity);
	}
	@Override
	public List<ProjectEntity> selectAllProjects() {
		return projectRepo.findAll();
	}
	
	@Override
	public List<ProjectEntity> selectListByUserId(Pageable pageable,String userId) {
		return projectRepo.findByCreatorId(pageable,userId);
	}
	
	@Override
	public void delete(Long projectId) {
		projectRepo.deleteById(projectId);
	}
	
	
	/* -------------------------------------------------------- */
	@Override 
	public List<ProjectDTO> test() {
		return session.selectList("com.multi.metahouse.project.getProjectWithPrice");
	}

	@Override
	public List<ProjectDTO> findTopNByProjectReviewAvg(int limit) {
		return session.selectList("com.multi.metahouse.project.findOrderByReviewRating",limit);
	}
	
	/* ------------------------ YSH --------------------------- */
	@Override
	public List<ProjectDTO> findByCreatorId(String creatorId) {
		return session.selectList("com.multi.metahouse.project.findProjectInfo", creatorId);
	}

/*---------------------------- OSE -------------------------------*/
	
	@Override
	public List<ProjectDTO> Allproject(Map<String, Object> condition) {
		return session.selectList("com.multi.metahouse.project.allProject", condition);
	}
	@Override
	public ProjectDTO projectInfo(Long project_id) {
		return session.selectOne("com.multi.metahouse.project.findProject", project_id);
	}
	@Override
	public List<ProjectContentsDTO> projectImg(Long project_id) {
		List<ProjectContentsDTO> list = session.selectList("com.multi.metahouse.project.findProjectImg", project_id);
		System.out.println("다오오오오오오 : 	" + list);
//		return session.selectList("com.multi.metahouse.project.findProjectImg", project_id);
		
		return list;
	}
	@Override
	public List<ProjectAddOption> projectOption(Long project_id) {
		return session.selectList("com.multi.metahouse.project.findProjectOptioin", project_id);
	}
	@Override
	public  Map<String, Integer> projectReviewSummary(int project_id) {
		return session.selectOne("com.multi.metahouse.domain.dao.ReviewDAO.ProjectReviewSummarybyid", project_id);
	}

}
