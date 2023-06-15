package com.multi.metahouse.review.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.multi.metahouse.domain.dto.review.ReviewCommentDTO;

public class ReviewCommentDAOImpl implements ReviewCommentDAO {
    
	SqlSession sqlSession;

    public ReviewCommentDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void saveReviewComment(ReviewCommentDTO reviewComment) {
        sqlSession.insert("saveReviewComment", reviewComment);
    }

    @Override
    public void updateReviewComment(ReviewCommentDTO reviewComment) {
        sqlSession.update("updateReviewComment", reviewComment);
    }

    @Override
    public void deleteReviewComment(int reviewCommentId) {
        sqlSession.delete("deleteReviewComment", reviewCommentId);
    }

    @Override
    public ReviewCommentDTO getReviewCommentById(int reviewCommentId) {
        return sqlSession.selectOne("getReviewCommentById", reviewCommentId);
    }

    @Override
    public List<ReviewCommentDTO> getReviewCommentsByReviewId(int reviewId) {
        return sqlSession.selectList("getReviewCommentsByReviewId", reviewId);
    }
}
