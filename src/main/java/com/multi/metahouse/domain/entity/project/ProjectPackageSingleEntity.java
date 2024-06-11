package com.multi.metahouse.domain.entity.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
@ToString(exclude = "projectId" )
@Entity
@Table(name="project_package_single")
public class ProjectPackageSingleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_package_single_id")
	private Long projectPackageSingleId;
//	@Column(name="project_id")
//	private Long projectId;
	private String pkgTitle;
	private String pkgDescription;
	private int price;
	private int revision;
	private int workdays;
	
	
	@OneToOne
	@JoinColumn(name = "project_id")
	private ProjectEntity projectId;
	
}
