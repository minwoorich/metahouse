package com.multi.metahouse.domain.dto.review;

import java.sql.Timestamp;

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
}
