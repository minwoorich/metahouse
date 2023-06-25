package com.multi.metahouse.domain.entity.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor 
@Data
@Entity
@Table(name="project_package_triple")
public class ProjectPackageTripleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_package_triple_id")
	private Long projectPackageTripleId;
	
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id")
	private ProjectEntity projectId;
}
