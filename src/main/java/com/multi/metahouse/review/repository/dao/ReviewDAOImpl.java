package com.multi.metahouse.review.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.multi.metahouse.domain.dto.review.ReviewDTO;

public class ReviewDAOImpl implements ReviewDAO {

	 
	    SqlSession sqlSession;
	    @Autowired
	    public ReviewDAOImpl(SqlSession sqlSession) {
	        this.sqlSession = sqlSession;
	    }

	    @Override
	    public void saveReview(ReviewDTO review) {
	        sqlSession.insert("saveReview", review);
	    }

	    @Override
	    public void updateReview(ReviewDTO review) {
	        sqlSession.update("updateReview", review);
	    }

	    @Override
	    public void deleteReview(int reviewId) {
	        sqlSession.delete("deleteReview", reviewId);
	    }

	    @Override
	    public ReviewDTO getReviewById(int reviewId) {
	        return sqlSession.selectOne("getReviewById", reviewId);
	    }

	    @Override
	    public List<ReviewDTO> getAllReviews() {
	        return sqlSession.selectList("getAllReviews");
	    }
	}
