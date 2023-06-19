package com.multi.metahouse.domain.dto.project;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFormDTO {
	private String creator_id;
	private String title;
	private String description;
	private String category1;
	private String category2_pj;
	private MultipartFile thumbnail;
	private List<MultipartFile> detailImages;
	//미리보기용 이미지 경로
	private List<String> filePath;
}
