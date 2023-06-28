package com.multi.metahouse.domain.dto.project;

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
	private String project_content_id;
	private String project_id;
	private String project_store_filename;
	private String project_file_no;
	
	public ProjectContentsEntity toEntity() {
		return ProjectContentsEntity.builder()
				.projectStoreFilename(projectStoreFilename)
				.projectFileNo(projectFileNo)
				.build();
	}
}
