package com.multi.metahouse.review.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewDTO;
import com.multi.metahouse.domain.dto.review.UnionReviewDTO;


public interface ReviewDAO {
    void saveReview(ReviewDTO review);//리뷰를 저장한다.
    void updateReview(ReviewDTO review);//리뷰를 업데이트한다.
    void deleteReview(int reviewId);//주어진 리뷰 ID에 해당하는 리뷰를 삭제합니다.
    ReviewDTO getReviewById(int reviewId);//주어진 리뷰 ID에 해당하는 리뷰를 가져온다
    List<ReviewDTO> getAllReviews();//모든리뷰를 가져온다.
	void createReview(ReviewDTO reviewDTO);
	
	//lch
	public List<UnionReviewDTO> findOrderByDate(int limit);
}

