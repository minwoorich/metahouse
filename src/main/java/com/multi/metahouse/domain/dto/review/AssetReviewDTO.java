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
@Alias("assetreview")
public class AssetReviewDTO {
	private int asset_review_id;
	private int asset_order_id;
	private String writer_id;
	private int asset_id;
	private int rating;
	private String review_text;
	private Timestamp review_date;
	//리뷰 답글 담기
	private ReviewCommentDTO reviewComment;
	//리뷰 이미지 담기
	private List<ReviewContentsDTO> reviewImg;
}
