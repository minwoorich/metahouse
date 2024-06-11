package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("unionreview")
public class UnionReviewDTO {
	int product_id;
	String title;
	String writer_id;
	String review_text;
	int rating;
	Timestamp review_date;
	String review_type;
	String profile_img;
}
