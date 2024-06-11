package com.multi.metahouse.review.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;

@Repository
public class ReviewCommentDAOImpl implements ReviewCommentDAO {

	SqlSession sqlSession;

	@Autowired
	public ReviewCommentDAOImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	// 답글 생성
	// asset
	@Override
	public void saveReviewCommentA(ReviewCommentDTO reviewComment) {
		sqlSession.insert("com.multi.metahouse.reciewcomment.saveCommentA", reviewComment);
	}

	// project
	@Override
	public void saveReviewCommentP(ReviewCommentDTO reviewComment) {
		sqlSession.insert("com.multi.metahouse.reciewcomment.saveCommentP", reviewComment);
	}

	// 답글 수정
	@Override
	public void updateReviewComment(ReviewCommentDTO reviewComment) {
		sqlSession.update("updateReviewComment", reviewComment);
	}

	// 답글 삭제
	@Override
	public void deleteReviewComment(int reviewCommentId) {
		sqlSession.delete("deleteReviewComment", reviewCommentId);
	}

	// 답글 조회(1개)
	@Override
	public ReviewCommentDTO getReviewCommentById(int reviewCommentId) {
		return sqlSession.selectOne("getReviewCommentById", reviewCommentId);
	}

	// 답글 조회(n개)
	@Override
	public List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId) {
		return sqlSession.selectList("getReviewCommentsByReviewId", reviewId);
	}

}
