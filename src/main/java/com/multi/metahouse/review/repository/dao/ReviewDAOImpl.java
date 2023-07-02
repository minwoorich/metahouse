package com.multi.metahouse.review.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.review.AssetReviewDTO;
import com.multi.metahouse.domain.dto.review.ProjectReviewDTO;
import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;
import com.multi.metahouse.domain.dto.review.ReviewDTO;
import com.multi.metahouse.domain.dto.review.UnionReviewDTO;

@Repository
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

	@Override
	public void createReview(ReviewDTO reviewDTO) {
		// TODO Auto-generated method stub

	}

	// LCH
	@Override
	public List<UnionReviewDTO> findOrderByDate(int limit) {
		return sqlSession.selectList("com.multi.metahouse.unionreview.findOrderByDate", limit);
	}

	/*---------------------------------- OSE -------------------------------------*/
	// 에셋 리뷰 조회
	@Override
	public List<AssetReviewDTO> getAllReviewsByAsset(String AssetId) {
		return sqlSession.selectList("com.multi.metahouse.domain.dao.ReviewDAO.getAllReviewsByAid", AssetId);
	}

	// 프로젝트 리뷰 조회
	@Override
	public List<ProjectReviewDTO> getAllReviewsByPJT(Long projectId) {
		return sqlSession.selectList("com.multi.metahouse.domain.dao.ReviewDAO.getAllReviewsByPJTid", projectId);
	}

	// 리뷰 이미지 조회
	@Override
	public List<ReviewContentsDTO> getAllReviewsImg(int review_id, String tag) {
		List<ReviewContentsDTO> reviewImgs = null;
		if (tag == "a") {
			reviewImgs = sqlSession.selectList("com.multi.metahouse.domain.dao.ReviewDAO.getAllAreviewImgByRewviewid",
					review_id);
		} else {
			reviewImgs = sqlSession.selectList("com.multi.metahouse.domain.dao.ReviewDAO.getAllPreviewImgByRewviewid",
					review_id);
		}
		return reviewImgs;
	}

}
