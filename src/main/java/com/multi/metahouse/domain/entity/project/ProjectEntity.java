package com.multi.metahouse.domain.entity.project;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import com.multi.metahouse.domain.entity.review.ProjectReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Long projectId;
	@Column(name = "creator_id")
	private String creatorId;
	private String tag;
	private String title;
	private String description;
	@CreationTimestamp
	@Column(name="project_date")
	private LocalDateTime projectDate;
	private String category1;
	@Column(name = "category2_pj")
	private String category2Pj;
	private String thumbnail;
	

////////////////자식 참조하기///////////////////////////////////////////////////////
	//양방향
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "projectId")
	private ProjectPackageSingleEntity singleEntity = new ProjectPackageSingleEntity(); 

	//양방향
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "projectId" )
	private ProjectPackageTripleEntity tripleEntity = new ProjectPackageTripleEntity(); 
	
	//양방향
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "projectId")
	private List<AddOptionEntity> addOptionEntityList = new ArrayList<>();
	
	//양방향
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
	private List<ProjectContentsEntity> projectContentsEntityList = new ArrayList<>();
	
	//단방향
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "projectId")
	private List<ProjectReviewEntity> reviewEntityList = new ArrayList<>();
	
	

}
