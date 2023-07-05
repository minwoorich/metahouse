package com.multi.metahouse.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.dto.user.UserDTO;
import com.multi.metahouse.domain.entity.project.AddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageSingleEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectFormDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectListDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectPackageForm;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectPackageSingleForm;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectPackageTripleForm;
import com.multi.metahouse.project.repository.dao.ProjectDAO;
import com.multi.metahouse.project.repository.jpa.ProjectRepository;

import ch.qos.logback.classic.pattern.Util;

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

////////////////////민우 영역////////////////////////////////////////////////////////////////////////
	@Override // 판매자가 본인의 서비스 프로젝트 등록
	public void insertProjectInfo(ProjectFormDTO projectFormDto, ProjectPackageForm packageFormDto,
			String thumbnailPath, List<ProjectContentsDTO> contentsList) {

		// 프로젝트 엔티티
		ProjectEntity projectEntity = ProjectEntity.builder().creatorId(projectFormDto.getCreator_id())
//				.creatorId(new UserDTO().toEntityOnlyId(projectFormDto.getCreator_id()))
				.title(projectFormDto.getTitle()).description(projectFormDto.getDescription())
				.category1(projectFormDto.getCategory1()).category2Pj(projectFormDto.getCategory2_pj())
				.thumbnail(thumbnailPath).build();
		System.out.println("컨텐츠 리스트 : " + projectEntity.getProjectContentsEntityList());

		// 싱글 패키지 엔티티
		if (packageFormDto instanceof ProjectPackageSingleForm) {
			ProjectPackageSingleEntity singleEntity = ((ProjectPackageSingleForm) packageFormDto).toEntity();
			singleEntity.setProjectId(projectEntity);
			// 프로젝트 엔티티에 추가
			projectEntity.setSingleEntity(singleEntity);
			// 삼단 패키지 엔티티
		} else {
			ProjectPackageTripleEntity tripleEntity = ((ProjectPackageTripleForm) packageFormDto).toEntity();
			tripleEntity.setProjectId(projectEntity);
			// 프로젝트 엔티티에 추가
			projectEntity.setTripleEntity(tripleEntity);
		}
		// 컨텐츠 엔티티
		if (contentsList != null) {
			for (ProjectContentsDTO dto : contentsList) {
				ProjectContentsEntity contentsEntity = dto.toEntity();
				contentsEntity.setProjectId(projectEntity);
				// 프로젝트 엔티티에 추가
				projectEntity.getProjectContentsEntityList().add(contentsEntity);
			}
		}

		// 추가 옵션 엔티티
		if (packageFormDto instanceof ProjectPackageSingleForm) {
			if (((ProjectPackageSingleForm) packageFormDto).getProjectAddOptionList() != null) {
				for (ProjectAddOption optionDto : ((ProjectPackageSingleForm) packageFormDto)
						.getProjectAddOptionList()) {

					AddOptionEntity optionEntity = optionDto.toEntity();
					optionEntity.setProjectId(projectEntity);
					// 프로젝트 엔티티에 추가
					projectEntity.getAddOptionEntityList().add(optionEntity);
				}
			}
		} else {
			if (((ProjectPackageTripleForm) packageFormDto).getProjectAddOptionList() != null) {
				for (ProjectAddOption optionDto : ((ProjectPackageTripleForm) packageFormDto)
						.getProjectAddOptionList()) {
					AddOptionEntity optionEntity = optionDto.toEntity();
					optionEntity.setProjectId(projectEntity);
					// 프로젝트 엔티티에 추가
					projectEntity.getAddOptionEntityList().add(optionEntity);
				}
			}

		}
		projectDao.insert(projectEntity);
	}

	@Override // 모든 프로젝트 가져오기(테스트용)
	public List<ProjectListDTO> selectAllProjects() {
		List<ProjectEntity> entityList = projectDao.selectAllProjects();
		List<ProjectListDTO> dto = new ArrayList<ProjectListDTO>();
		for (ProjectEntity entity : entityList) {
			dto.add((new ProjectListDTO()).fromEntity(entity));
		}

		return dto;
	}

	@Override // 유저 ID로 프로젝트 가져오기
	public List<ProjectListDTO> selectListByUserId(String userId) {
		// DAO 호출해서 Entity리스트 받아옴
		List<ProjectEntity> entityList = projectDao.selectListByUserId(userId);
		// 반환할 빈 DTO리스트 생성.
		List<ProjectListDTO> dtoList = new ArrayList<>();

		for (ProjectEntity entity : entityList) {
			dtoList.add(new ProjectListDTO().fromEntity(entity));
		}
		return dtoList;
	}

	@Override
	public void deleteProject(Long projectId) {
		projectDao.delete(projectId);
	}
	/*------------------------------------------ OSE -------------------------------------------*/

	// 프로젝트 상품리스트 출력: 카테고리 값 받아서 출력해주기
	@Override
	public List<ProjectDTO> list(Integer currnetPage, String category, String category2) {
		Map<String, Object> condition = new HashMap<String, Object>();
		Integer skip = currnetPage;
		String category1 = category;
		String category2pj = category2;
		condition.put("skip", skip);
		condition.put("category1", category1);
		condition.put("category2_pj", category2pj);
		List<ProjectDTO> projectsInPage = (List<ProjectDTO>) projectDao.Allproject(condition);
		if(currnetPage != null) {
			for (int i = 0; i < projectsInPage.size(); i++) {
				int projectId = projectsInPage.get(i).getProject_id();
				Map<String, Integer> reviewSummary = projectDao.projectReviewSummary(projectId);
				/* java.lang.Long cannot be cast to java.lang.Integer 에러 발생 
				 * : long 형(db 기준 number)을 바로 int로 변환하려고 하기 때문 */
				int reviewCount = Integer.parseInt(String.valueOf(reviewSummary.get("Review_count")));
				double reviewAvg = 0;
				if (reviewCount > 0) {
					reviewAvg = Double.parseDouble(String.valueOf(reviewSummary.get("Review_Avg")));
				}
				projectsInPage.get(i).setReview_count(reviewCount);
				projectsInPage.get(i).setAverage_reviews(reviewAvg);
			}
		}
		return projectsInPage;
	}

	@Override
	public ProjectDTO projectInfo(Long projectNum) {
		return projectDao.projectInfo(projectNum);
	}

	@Override
	public List<ProjectContentsDTO> projectImg(Long projectNum) {
		return projectDao.projectImg(projectNum);
	}

	@Override
	public List<ProjectAddOption> projectOption(Long projectNum) {
		return projectDao.projectOption(projectNum);
	}

}
