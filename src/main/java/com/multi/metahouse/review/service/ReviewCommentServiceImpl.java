package com.multi.metahouse.review.service;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;
import com.multi.metahouse.review.repository.dao.ReviewCommentDAO;

public class ReviewCommentServiceImpl implements ReviewCommentService {

	private ReviewCommentDAO reviewCommentdao;

    // 생성자를 통해 ReviewCommentRepository 의존성 주입
    
    @Override
    public ReviewCommentDTO getReviewCommentById(int reviewCommentId) {
        // Repository를 사용하여 해당 reviewCommentId에 해당하는 Review Comment 조회 및 반환
        return reviewCommentdao.getReviewCommentById(reviewCommentId);
    }
    
    @Override
    public List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId) {
        // Repository를 사용하여 해당 reviewId에 해당하는 Review Comment 목록 조회 및 반환
        return reviewCommentdao.getReviewCommentsByReviewId(reviewId);
    }

    @Override
    public void addReviewComment(ReviewCommentDTO reviewComment) {
        // Repository를 사용하여 Review Comment 추가
    	reviewCommentdao.addReviewComment(reviewComment);
    }

    @Override
    public void updateReviewComment(ReviewCommentDTO reviewComment) {
        // Repository를 사용하여 Review Comment 업데이트
    	reviewCommentdao.updateReviewComment(reviewComment);
    }

    @Override
    public void deleteReviewComment(int reviewCommentId) {
        // Repository를 사용하여 Review Comment 삭제
    	reviewCommentdao.deleteReviewComment(reviewCommentId);
    }
}
