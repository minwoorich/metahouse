package com.multi.metahouse.domain.dto.project;

import javax.persistence.Column;

import com.multi.metahouse.domain.entity.project.ProjectContentsEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjectContentsDTO {
	private String projectStoreFilename;
	private int projectFileNo;
	
	public ProjectContentsEntity toEntity() {
		return ProjectContentsEntity.builder()
				.projectStoreFilename(projectStoreFilename)
				.projectFileNo(projectFileNo)
				.build();
	}
}
