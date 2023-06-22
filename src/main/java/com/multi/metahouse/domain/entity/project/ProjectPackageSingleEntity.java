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
@Table(name="project_package_single")
public class ProjectPackageSingleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private Long projectId;
	@Column(name="pkg_title")
	private String pkgTitle;
	@Column(name="pkg_description")
	private String pkgDescription;
	private int price;
	private int revision;
	private int workdays;
}
