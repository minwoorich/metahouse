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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@DynamicInsert
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
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
	private LocalDateTime projectDate;
	private String category1;
	@Column(name = "category2_pj")
	private String category2Pj;
	private String thumbnail;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "creator_id")
//	User creatorId;
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
	private List<ProjectContentsEntity> projectContentsEntityList = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
	private ProjectPackageSingleEntity singleEntity = new ProjectPackageSingleEntity();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
	private ProjectPackageTripleEntity tripleEntity = new ProjectPackageTripleEntity();
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "projectId")
	private List<AddOptionEntity> addOptionEntityList = new ArrayList<>();

}
