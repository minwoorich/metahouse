package com.multi.metahouse.domain.dto.review;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("reviewImg")
public class ReviewContentsDTO {
	private int review_contents_id;
	private int review_id;
	private String review_store_filename;
	private Integer review_img_no;
}
