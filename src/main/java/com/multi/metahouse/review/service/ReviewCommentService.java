package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;

public interface ReviewCommentService {
	public void saveReviewComment(ReviewCommentDTO reviewComment, String tag) ;

	public void updateReviewComment(ReviewCommentDTO reviewComment);

	public void deleteReviewComment(int reviewCommentId);

	public ReviewCommentDTO getReviewCommentById(int reviewCommentId);

	public List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId);
}
