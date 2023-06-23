package com.multi.metahouse.domain.entity.project;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="project")
public class ProjectEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	@Column(name="creator_id")
	private String creatorId;
	private String tag;
	private String title;
	private String description;
	@CreationTimestamp
	private LocalDateTime projectDate;
	private String category1;
	@Column(name="category2_pj")
	private String category2Pj;
	private String thumbnail; 
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="creator_id")
//	private User user;
	
	//mappedBy = "projectEntity", 
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="project_id")
	private List<ProjectContentsEntity> projectContentsEntityList;
	
	@OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="project_id")
	private ProjectPackageSingleEntity singleEntity;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private ProjectPackageTripleEntity tripleEntity;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<AddOptionEntity> addOptionEntityList = new ArrayList<>();

	
}
