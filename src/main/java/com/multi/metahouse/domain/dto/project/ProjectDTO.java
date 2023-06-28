package com.multi.metahouse.domain.dto.project;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.dto.user.UserDTO;
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
	private int project_id;
	private String creator_id;
	private String tag;
	private String title;
	private String description;
	private String project_thumbnail_img;
	private Timestamp project_date;
	private int project_hits;
	private String category1;
	private String category2_pj;

	//프로젝트 패키지 정보 저장을 위한 변수	
	private ProjectPackageSingleForm pjtSingle;
	private ProjectPackageTripleForm pjtTriple;
	//판매자 정보 저장을 위한 변수
	private UserDTO creator;
	
	
	
	//extra data
	//단일패키지 가격 
	private int price;
	//리뷰갯수	
	private int review_count;
	//리뷰평균
	private double average_reviews;
	public ProjectDTO(int project_id, String creator_id, String tag, String title, String description,
			Timestamp project_date, int project_hits, String category1, String category2_pj, int price,
			int review_count, double average_reviews) {
		super();
		this.project_id = project_id;
		this.creator_id = creator_id;
		this.tag = tag;
		this.title = title;
		this.description = description;
		this.project_date = project_date;
		this.project_hits = project_hits;
		this.category1 = category1;
		this.category2_pj = category2_pj;
		this.price = price;
		this.review_count = review_count;
		this.average_reviews = average_reviews;
	}
	
	
	
}
