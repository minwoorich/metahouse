package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("projectreview")
public class ProjectReviewDTO {
	private int project_review_id;
	private int project_order_id;
	private String writer_id;
	private int project_id;
	private int rating;
	private String review_text;
	private Timestamp review_date;
}
