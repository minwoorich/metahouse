package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("projectreviewImg")
public class ProjectReviewContentsDTO {
	private int review_contents_id;
	private int project_review_id;
	private String order_id;
	private String user_id;
	private int project_id;
	private String review_store_filename;
	private String review_img_no;

	
}
