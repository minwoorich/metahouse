package com.multi.metahouse.review.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;

public class ReviewContentsDAOImpl implements ReviewContentsDAO {
    
	SqlSession sqlSession;

    public ReviewContentsDAOImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public void saveReviewContents(ReviewContentsDTO reviewContents) {
        sqlSession.insert("saveReviewContents", reviewContents);
    }

    @Override
    public void updateReviewContents(ReviewContentsDTO reviewContents) {
        sqlSession.update("updateReviewContents", reviewContents);
    }

    @Override
    public void deleteReviewContents(int reviewContentsId) {
        sqlSession.delete("deleteReviewContents", reviewContentsId);
    }

    @Override
    public ReviewContentsDTO getReviewContentsById(int reviewContentsId) {
        return sqlSession.selectOne("getReviewContentsById", reviewContentsId);
    }

    @Override
    public List<ReviewContentsDTO> getReviewContentsByReviewId(int reviewId) {
        return sqlSession.selectList("getReviewContentsByReviewId", reviewId);
    }
}
