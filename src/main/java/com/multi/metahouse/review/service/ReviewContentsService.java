package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;

public interface ReviewContentsService {
	 ReviewContentsDTO getReviewContentsById(int reviewContentsId);
	    
	    List<ReviewContentsDTO> getReviewContentsByReviewId(int reviewId);

	    void addReviewContents(ReviewContentsDTO reviewContents);

	    void updateReviewContents(ReviewContentsDTO reviewContents);

	    void deleteReviewContents(int reviewContentsId);
}
