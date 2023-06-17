package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;

public interface ReviewCommentService {

ReviewCommentDTO getReviewCommentById(int reviewCommentId);
    
    List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId);

    void addReviewComment(ReviewCommentDTO reviewComment);

    void updateReviewComment(ReviewCommentDTO reviewComment);

    void deleteReviewComment(int reviewCommentId);
}
