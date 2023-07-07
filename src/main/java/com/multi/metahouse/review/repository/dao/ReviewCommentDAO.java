package com.multi.metahouse.review.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;



public interface ReviewCommentDAO {
	public void saveReviewCommentA(ReviewCommentDTO reviewComment);//리뷰 댓글을 저장한다.(asset)
	public void saveReviewCommentP(ReviewCommentDTO reviewComment);//리뷰 댓글을 저장한다.(project)
    void updateReviewComment(ReviewCommentDTO reviewComment);//리뷰 댓글을 업데이트한다
    void deleteReviewComment(int reviewCommentId);//주어진 리뷰 댓글 ID에 해당하는 리뷰 댓글을 삭제합니다.
    ReviewCommentDTO getReviewCommentById(int reviewCommentId);//주어진 리뷰 댓글 ID에 해당하는 리뷰 댓글을 가져온다.
    List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId);//주어진 리뷰 ID에 해당하는 모든 리뷰 댓글을 가져온다.
}


