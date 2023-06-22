package com.multi.metahouse.domain.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data
@Entity
@Table(name="project_package_triple")
public class ProjectPackageTripleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	@Column(name="basic_pkg_title")
	private String basicPkgTitle;
	@Column(name="basic_pkg_description")
	private String basicPkgDescription;
	@Column(name="basic_price")
	private int basicPrice;
	@Column(name="basic_revision")
	private int basicRevision;
	@Column(name="basic_workdays")
	private int basicWorkdays;
	
	@Column(name="economy_pkg_title")
	private String economyPkgTitle;
	@Column(name="economy_pkg_description")
	private String economyPkgDescription;
	@Column(name="economy_price")
	private int economyPrice;
	@Column(name="economy_revision")
	private int economyRevision;
	@Column(name="economy_workdays")
	private int economyWorkdays;
	
	@Column(name="premium_pkg_title")
	private String premiumPkgTitle;
	@Column(name="premium_pkg_description")
	private String premiumPkgDescription;
	@Column(name="premium_price")
	private int premiumPrice;
	@Column(name="premium_revision")
	private int premiumRevision;
	@Column(name="premium_workdays")
	private int premiumWorkdays;
}
