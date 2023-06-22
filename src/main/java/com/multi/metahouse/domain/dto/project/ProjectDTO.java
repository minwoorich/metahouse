package com.multi.metahouse.domain.dto.project;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.entity.project.ProjectEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("project")
public class ProjectDTO {
	//column
	int project_id;
	String creator_id;
	String tag;
	String title;
	String description;
	String project_thumbnail_img;
	Timestamp project_date;
	int project_hits;
	String category1;
	String category2_pj;
	
	
	//extra data
	int price;
}
