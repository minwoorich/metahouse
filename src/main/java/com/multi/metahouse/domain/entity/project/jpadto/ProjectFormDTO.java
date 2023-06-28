package com.multi.metahouse.domain.entity.project.jpadto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.entity.project.ProjectEntity;
import com.multi.metahouse.domain.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProjectFormDTO {
	private String creator_id;
	private String title;
	private String description;
	private String category1;
	private String category2_pj;
	private MultipartFile thumbnail;
	private List<MultipartFile> detailImages;
	
	public ProjectEntity toEntity() {
		return ProjectEntity.builder()
				.creatorId(creator_id)
				.title(title)
				.description(description)
				.category1(category1)
				.category2Pj(category2_pj)
				.build();
	}
}
