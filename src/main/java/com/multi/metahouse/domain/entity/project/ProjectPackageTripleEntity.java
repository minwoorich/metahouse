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
@Table(name="project_package_triple")
public class ProjectPackageTripleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_package_triple_id")
	private Long projectPackageTripleId;
//	@Column(name="project_id")
//	private Long projectId;
	private String basicPkgTitle;
	private String basicPkgDescription;
	private int basicPrice;
	private int basicRevision;
	private int basicWorkdays;
	
	private String economyPkgTitle;
	private String economyPkgDescription;
	private int economyPrice;
	private int economyRevision;
	private int economyWorkdays;
	
	private String premiumPkgTitle;
	private String premiumPkgDescription;
	private int premiumPrice;
	private int premiumRevision;
	private int premiumWorkdays;
	
	
	@OneToOne
	@JoinColumn(name = "project_id")
	private ProjectEntity projectId;
}
