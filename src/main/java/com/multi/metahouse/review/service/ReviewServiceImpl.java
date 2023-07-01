package com.multi.metahouse.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.dto.review.ProjectReviewDTO;
import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;
import com.multi.metahouse.domain.dto.review.ProjectReviewContentsDTO;
import com.multi.metahouse.domain.dto.review.ReviewDTO;
import com.multi.metahouse.review.repository.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDAO reviewDAO;

	@Autowired
	public ReviewServiceImpl(ReviewDAO reviewDAO) {
		this.reviewDAO = reviewDAO;
	}

	@Override
	public ReviewDTO getReviewById(int reviewId) {
		return reviewDAO.getReviewById(reviewId);
	}

	@Override
	public List<ReviewDTO> getAllReviews() {
		return reviewDAO.getAllReviews();
	}

	@Override
	public void createReview(ReviewDTO reviewDTO) {
		reviewDAO.createReview(reviewDTO);
	}

	@Override
	public void updateReview(ReviewDTO reviewDTO) {
		reviewDAO.updateReview(reviewDTO);
	}

	@Override
	public void deleteReview(int reviewId) {
		reviewDAO.deleteReview(reviewId);
	}

	/*-------------------- OSE ------------------*/
	@Override
	@Transactional
	public List<ProjectReviewDTO> getAllReviewsByPJTid(Long projectId) {
		List<ProjectReviewDTO> PJTreviews = reviewDAO.getAllReviewsByPJT(projectId);
		int PJTreviewId = 0;
		for (int i = 0; i < PJTreviews.size(); i++) {
			PJTreviewId = PJTreviews.get(i).getProject_review_id();
			List<ProjectReviewContentsDTO> reviewImg = reviewDAO.getAllReviewsImg(PJTreviewId);
			if(reviewImg.size()>0){
				PJTreviews.get(i).setReviewImg(reviewImg);
			}
		}
				
		return PJTreviews;
	}
}