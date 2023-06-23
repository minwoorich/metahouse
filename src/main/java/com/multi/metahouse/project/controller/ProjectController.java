package com.multi.metahouse.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.project.ProjectAddOption;
import com.multi.metahouse.domain.dto.project.ProjectContentsDTO;
import com.multi.metahouse.domain.dto.project.ProjectFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectPackageForm;
import com.multi.metahouse.domain.dto.project.ProjectPackageSingleForm;
import com.multi.metahouse.domain.dto.project.ProjectPackageTripleForm;
import com.multi.metahouse.domain.entity.project.AddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;
import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageSingleEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.project.service.ProjectFileUploadLogicService;
import com.multi.metahouse.project.service.ProjectService;

@Controller
public class ProjectController {
	ProjectFileUploadLogicService fileService;
	ProjectService projectService;
	ResourceLoader resourceLoader;

	@Autowired
	public ProjectController(ProjectFileUploadLogicService fileService, ProjectService projectService,
			ResourceLoader resourceLoader) {
		super();
		this.fileService = fileService;
		this.projectService = projectService;
		this.resourceLoader = resourceLoader;
	}


	////////////////////// 승언님 파트////////////////////////
//	프로젝트 마켓 상품목록 보기
	@RequestMapping("project/main")
	public String gigs1() {
		return "project/main";
	}

	// 프로젝트 상세보기
	@RequestMapping("project/detail")
	public String showGigs1() {
		return "project/market_detail";
	}

//	프로젝트 구매하기
	@GetMapping("project/purchase")
	public String puchaseGigs() {
		return "order/project_purchase";
	}

//	프로젝트 구인 상품목록 보기
	@RequestMapping("project/main2")
	public String gigs2() {
		return "project/z_project2_main";
	}

//	프로젝트 구인 상세페이지
	@RequestMapping("project/detail2")
	public String showGigs2() {
		return "project/z_project2_detail";
	}

	/////////////////////////// 민우님 파트////////////////////////////////
	// 프로젝트 설명 입력하는 페이지 반환
	@GetMapping("project/forms/descriptions")
	public String writeForm(HttpSession session) {
		session.setAttribute("creator_id", "user1");
		
		return "project/projectform01";
	}

	// 패키지 + 추가옵션 설정하는 페이지 반환
	@GetMapping("project/forms/packages")
	public String writePakcageForm(HttpSession session) {
		System.out.println("session : " + session.getAttribute("projectForm"));
		return "project/projectform02";
	}

	@GetMapping("project/forms/preview")
	public String getFormPreview(HttpSession session, Model model) {
		
		// 세션에 삼단패키지가 들어갔는지 ,단일패키지가 들어갔는지
		if (session.getAttribute("projectPackageTripleForm") == null) {
			model.addAttribute("package", "single");
			List<ProjectAddOption> optionList = ((ProjectPackageSingleForm) session
					.getAttribute("projectPackageSingleForm")).getProjectAddOptionList();
			model.addAttribute("optionList", optionList);
		} else {
			model.addAttribute("package", "triple");
			List<ProjectAddOption> optionList = ((ProjectPackageTripleForm) session
					.getAttribute("projectPackageTripleForm")).getProjectAddOptionList();
			model.addAttribute("optionList", optionList);
		}

		return "project/projectform03";
	}

	@RequestMapping(value = "project/forms-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(HttpSession session, ProjectFormDTO projectForm) {
		
		// 세션 저장
		session.setAttribute("projectForm", projectForm);
		
		
		return "/metahaus/project/forms/packages";
	}

	@RequestMapping(value = "project/single-package-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(@RequestBody ProjectPackageSingleForm projectPackageSingleForm,
			HttpSession session) {
		
		// 세션 초기화(projectPackageSingelForm 데이터 세션에서 삭제)
		session.removeAttribute("projectPackageSingleForm");
		// 세션 저장
		session.setAttribute("projectPackageSingleForm", projectPackageSingleForm);

		return "/metahaus/project/forms/preview";
	}

	@RequestMapping(value = "project/triple-package-ajax", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveIntoSessionAjax(@RequestBody ProjectPackageTripleForm projectPackageTripleForm,
			HttpSession session) {
		
		// 세션 초기화(projectPackageTripleForm 데이터 세션에서 삭제)
		session.removeAttribute("projectPackageTripleForm");
		// 세션 저장
		session.setAttribute("projectPackageTripleForm", projectPackageTripleForm);

		return "/metahaus/project/forms/preview";
	}
	
	@GetMapping("project/forms")
	public String insertForm(HttpSession session) throws IllegalStateException, IOException {
//		
//		  ProjectPackageForm packageFormDto = null; List<ProjectContentsEntity>
//		  contentsList = new ArrayList<>();
//		  
//		  /////////////세션에서 DTO 추출///////////////// // 세션에서 데이터 추출 - projectForm
//		  ProjectFormDTO projectForm = (ProjectFormDTO)
//		  session.getAttribute("projectForm");
//		  
//		  // 세션에서 데이터 추출 - packageFormDto(단일,삼단 패키지) if
//		  (session.getAttribute("projectPackageSingleForm") != null) { packageFormDto =
//		  (ProjectPackageSingleForm) session.getAttribute("projectPackageSingleForm");
//		  
//		  } else { packageFormDto = (ProjectPackageTripleForm)
//		  session.getAttribute("projectPackageTripleForm"); }
//		  
//		  ///////////////////DTO에서 이미지 파일 추출 및 업로드//////////////////////// // 썸네일 추출 &
//		  업로드 & DTO의 thumbnailPath 세팅 String path =
//		  resourceLoader.getResource("classpath:static/upload/projectImg").getFile().
//		  getAbsolutePath(); MultipartFile thumbnail = projectForm.getThumbnail();
//		  String thumbnailPath = fileService.uploadFile(thumbnail,path);
//		  
//		  //상세이미지들 추출 & 업로드 & ProjectContentsEntity 데이터 옮겨담기 if
//		  (!projectForm.getDetailImages().isEmpty()) { // 상세이미지들 추출 List<MultipartFile>
//		  detailImages = projectForm.getDetailImages(); // 상세이미지들 업로드 contentsList =
//		  fileService.uploadFiles(detailImages,path); }
//		  
//		  ////////////////////DTO -> ENTITY로 데이터 추출/////////////////////// // 0) 모든 엔티티
//		  객체 생성 ProjectEntity projectEntity = new ProjectEntity();
//		  ProjectPackageSingleEntity singleEntity = new ProjectPackageSingleEntity();
//		  ProjectPackageTripleEntity tripleEntity = new ProjectPackageTripleEntity();
//		  AddOptionEntity addOptionEntity = new AddOptionEntity();
//		  
//		  // 1) ProjectEntity에 데이터 옮겨담기 projectEntity =
//		  projectForm.toEntity();//DTO->엔티티 projectEntity.setThumbnail(thumbnailPath);
//		  // User user = new User(); // user.setUserId(projectForm.getCreator_id()); //
//		  projectEntity.setUser(user); List<AddOptionEntity> addOptionEntityList = new
//		  ArrayList<>();
//		  
//		  // 2) // ProjectPackageSingleEntity, // ProjectPackageTripleEntity, //
//		  AddOptionEntity 에 데이터 옮겨담기 if(packageFormDto instanceof
//		  ProjectPackageSingleForm) {//단일패키지인경우 // single DTO -> 엔티티 singleEntity =
//		  ((ProjectPackageSingleForm) packageFormDto).toEntity(); // single DTO 에 있던
//		  addOptionList 추출 List<ProjectAddOption> addOptionList =
//		  ((ProjectPackageSingleForm) packageFormDto).getProjectAddOptionList();
//		  
//		  // addOption DTO -> 엔티티 , 그리고 엔티티 리스트 생성 for(ProjectAddOption addOption :
//		  addOptionList) { addOptionEntity = addOption.toEntity();
//		  addOptionEntityList.add(addOptionEntity);//ProjectEntity에 추후에 담을 예정 } }else
//		  {//삼단패키지 인경우 // triple DTO -> 엔티티 tripleEntity =
//		  ((ProjectPackageTripleForm)packageFormDto).toEntity(); // triple DTO 에 있던
//		  addOptionList 추출 List<ProjectAddOption> addOptionList =
//		  ((ProjectPackageTripleForm) packageFormDto).getProjectAddOptionList();
//		  
//		  // addOption DTO -> 엔티티 , 그리고 엔티티 리스트 생성 for(ProjectAddOption addOption :
//		  addOptionList) { addOptionEntity = addOption.toEntity();
//		  addOptionEntityList.add(addOptionEntity);//ProjectEntity에 추후에 담을 예정 } }
//		  
//		  /////////////////ProjectEntity의 필드에 참조되어있는 타 엔티티에 데이터
//		  집어넣기//////////////////////////////////////////////////
//		  projectEntity.setProjectContentsEntityList(contentsList); if(packageFormDto
//		  instanceof ProjectPackageSingleForm) {
//		  projectEntity.setSingleEntity(singleEntity); }else {
//		  projectEntity.setTripleEntity(tripleEntity); }
//		  projectEntity.setAddOptionEntityList(addOptionEntityList);
//		  
//		  //엔티티 찍어보기 System.out.println(
//		  "===============================엔티티찍어보기===============================");
//		  System.out.println("ProjectEntity : " + projectEntity);
//		  
//		  for(ProjectContentsEntity entity : contentsList) {
//		  System.out.println("ProjectContentsEntity : " + entity); } if(packageFormDto
//		  instanceof ProjectPackageSingleForm) { System.out.println("singleEntity : " +
//		  singleEntity); }else { System.out.println("tripleEntity : " + tripleEntity);
//		  } for(AddOptionEntity entity : addOptionEntityList) {
//		  System.out.println("addOptionEntity : " + entity); }
//		  
//		  System.out.println();
//		  System.out.println("===================================================");
//		  System.out.println("ProjectEntity 내부 참조 엔티티값 찍어보기");
//		  System.out.println("ProjectEntity의 ProjectContentsEntity");
//		  for(ProjectContentsEntity entity :
//		  projectEntity.getProjectContentsEntityList()) { System.out.println(entity); }
//		  System.out.println(); if(packageFormDto instanceof ProjectPackageSingleForm)
//		  { System.out.println("ProjectEntity의 singleEntity" );
//		  System.out.println(projectEntity.getSingleEntity()); }else {
//		  System.out.println("ProjectEntity의 tripleEntity" );
//		  System.out.println(projectEntity.getTripleEntity()); } System.out.println();
//		  System.out.println("ProjectEntity의 AddOptionEntity"); for(AddOptionEntity
//		  entity : projectEntity.getAddOptionEntityList()) {
//		  System.out.println(entity); }
//		  
//		  System.out.println(
//		  "===================================================================");
		
		List<ProjectContentsEntity> contentsEntityList = new ArrayList<ProjectContentsEntity>();
		ProjectPackageSingleEntity singleEntity = new ProjectPackageSingleEntity();
		ProjectEntity projectEntity = new ProjectEntity(null,"user1",null,"제목","설명",null,"카테고리1","카테고리2","사진경로",
												contentsEntityList,
												new ProjectPackageSingleEntity(null,null,"패키지제목","패키지설명",1000,3,3));
		projectEntity.getProjectContentsEntityList().add(new ProjectContentsEntity(null,null,"상세사진경로",1));
		projectEntity.getProjectContentsEntityList().add(new ProjectContentsEntity(null,null,"상세사진경로",2));
		projectEntity.getProjectContentsEntityList().add(new ProjectContentsEntity(null,null,"상세사진경로",3));
		
		
		projectService.insertProjectInfo(projectEntity);
		 
		
		return "main/index";
	}

	//////////////////// 승민님 파트//////////////////////////
	@GetMapping("project/project-product")
	public String projectFormlist() {
		return "project/project_product_list";
	}
	/////////////////// 창훈님 파트///////////////////////

}