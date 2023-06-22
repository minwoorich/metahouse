package com.multi.metahouse.domain.entity.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.multi.metahouse.domain.dto.project.ProjectFormDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="project_content")
public class ProjectContentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_content_id")
	private Long projectContentId;
	@Column(name="project_id")
	private Long projectId;
	private String projectStoreFilename;
	private int projectFileNo;
	
	
	
}
