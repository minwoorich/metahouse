package com.multi.metahouse.domain.entity.project;

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
@Table(name="project_content")
public class ProjectContentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectContentId;
	private int projectId;
	private String projectStoreFilename;
	private int projectFileNo;
	
}
