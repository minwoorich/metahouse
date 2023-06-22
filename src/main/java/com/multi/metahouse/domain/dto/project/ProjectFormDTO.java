package com.multi.metahouse.domain.dto.project;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


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
	
	@Builder
	public ProjectFormDTO(String creator_id, String title, String description, String category1, String category2_pj) {
		super();
		this.creator_id = creator_id;
		this.title = title;
		this.description = description;
		this.category1 = category1;
		this.category2_pj = category2_pj;
	}
}
