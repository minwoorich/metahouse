package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewDTO;

public interface ReviewService {
	ReviewDTO getReviewById(int reviewId);

    List<ReviewDTO> getAllReviews();

    void createReview(ReviewDTO reviewDTO);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(int reviewId);
}
