package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.AssetReviewDTO;
import com.multi.metahouse.domain.dto.review.ProjectReviewDTO;
import com.multi.metahouse.domain.dto.review.ReviewDTO;
import com.multi.metahouse.domain.entity.review.jpadto.ProjectReviewJpaDto;

public interface ReviewService {
	ReviewDTO getReviewById(int reviewId);

    List<ReviewDTO> getAllReviews();

    void createReview(ReviewDTO reviewDTO);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(int reviewId);
    
    public List<AssetReviewDTO> getAllReviewsByAid(String assetId);
    public List<ProjectReviewDTO> getAllReviewsByPJTid(Long projectId);
	/* ---------------- 민우 영역----------------------- */
    void insertProjectReview(ProjectReviewJpaDto projectReviewDto);
    Long countByOrderIdAndWriterId(Long orderId, String writerId);
    
}
