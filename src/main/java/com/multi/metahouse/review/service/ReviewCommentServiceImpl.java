package com.multi.metahouse.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;
import com.multi.metahouse.review.repository.dao.ReviewCommentDAO;

@Service
public class ReviewCommentServiceImpl implements ReviewCommentService {

	private ReviewCommentDAO reviewCommentdao;

	@Autowired
	public ReviewCommentServiceImpl(ReviewCommentDAO reviewCommentdao) {
		super();
		this.reviewCommentdao = reviewCommentdao;
	}

	// 답글 생성
	@Override
	public void saveReviewComment(ReviewCommentDTO reviewComment, String tag) {
		if(tag.equals("\"asset\"")) {
			reviewCommentdao.saveReviewCommentA(reviewComment);
		}else {
			reviewCommentdao.saveReviewCommentP(reviewComment);
		}
	}

	// 답글 수정
	@Override
	public void updateReviewComment(ReviewCommentDTO reviewComment) {
		reviewCommentdao.updateReviewComment(reviewComment);
	}

	// 답글 삭제
	@Override
	public void deleteReviewComment(int reviewCommentId) {
		reviewCommentdao.deleteReviewComment(reviewCommentId);
	}

	// 답글 조회(1개)
	@Override
	public ReviewCommentDTO getReviewCommentById(int reviewCommentId) {
		return reviewCommentdao.getReviewCommentById(reviewCommentId);
	}

	// 답글 조회(n개)
	@Override
	public List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId) {
		return reviewCommentdao.getReviewCommentsByReviewId(reviewId);
	}
}
