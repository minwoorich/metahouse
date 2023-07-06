package com.multi.metahouse.domain.entity.review.jpadto;

import java.util.List;

import com.multi.metahouse.domain.entity.review.AssetReviewContentsEntity;
import com.multi.metahouse.domain.entity.review.AssetReviewEntity;
import com.multi.metahouse.domain.entity.review.jpadto.ProjectReviewJpaDto.ProjectReviewContents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class AssetReviewJpaDto {
	private Long orderId;
	private Long assetId;
	private String writerId;
	private Long rating;
	private String reviewText;
	private List<AssetReviewContents> contentsList;
	
	public AssetReviewEntity toEntity() {
		return AssetReviewEntity.builder()
				.assetOrderId(orderId)
				.writerId(writerId)
				.assetId(assetId)
				.rating(rating)
				.reviewText(reviewText)
				.build();
	}
	
	@Getter
	@AllArgsConstructor
	@Builder
	@ToString
	public static class AssetReviewContents{
		private String reviewStoreFilename;
		private Long reviewImgNo;
		
		public AssetReviewContentsEntity toEntity() {
			return AssetReviewContentsEntity.builder()
					.reviewStoreFilename(reviewStoreFilename)
					.reviewImgNo(reviewImgNo)
					.build();
		}
	}
}
