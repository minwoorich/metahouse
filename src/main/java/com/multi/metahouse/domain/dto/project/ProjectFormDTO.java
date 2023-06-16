package com.multi.metahouse.domain.dto.project;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectFormDTO {
	private int project_id;
	private String creator_id;
	private String title;
	private String description;
	private Timestamp project_date;
	private String category1;
	private String category2_pj;
	private List<MultipartFile> imageList;
	//미리보기용 이미지 경로
	private List<Map<String, String>> filePath;
}
