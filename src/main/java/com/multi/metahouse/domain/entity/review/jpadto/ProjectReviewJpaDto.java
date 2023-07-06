package com.multi.metahouse.domain.entity.review.jpadto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.entity.review.ProjectReviewContentsEntity;
import com.multi.metahouse.domain.entity.review.ProjectReviewEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
@AllArgsConstructor
public class ProjectReviewJpaDto {
	private Long orderId;
	private Long projectId;
	private String writerId;
	private Long rating;
	private String reviewText;
	private List<ProjectReviewContents> contentsList;
	
	public ProjectReviewEntity toEntity() {
		return ProjectReviewEntity.builder()
				.projectOrderId(orderId)
				.writerId(writerId)
				.projectId(projectId)
				.rating(rating)
				.reviewText(reviewText)
				.build();
	}
	
	@Getter
	@AllArgsConstructor
	@Builder
	@ToString
	public static class ProjectReviewContents{
		private String reviewStoreFilename;
		private Long reviewImgNo;
		
		public ProjectReviewContentsEntity toEntity() {
			return ProjectReviewContentsEntity.builder()
					.reviewStoreFilename(reviewStoreFilename)
					.reviewImgNo(reviewImgNo)
					.build();
		}
	}
}
