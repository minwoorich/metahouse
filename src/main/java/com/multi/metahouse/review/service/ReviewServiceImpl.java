package com.multi.metahouse.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.dto.review.AssetReviewDTO;
import com.multi.metahouse.domain.dto.review.ProjectReviewDTO;
import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;
import com.multi.metahouse.domain.dto.review.ReviewDTO;
import com.multi.metahouse.domain.entity.review.AssetReviewContentsEntity;
import com.multi.metahouse.domain.entity.review.AssetReviewEntity;
import com.multi.metahouse.domain.entity.review.ProjectReviewContentsEntity;
import com.multi.metahouse.domain.entity.review.ProjectReviewEntity;
import com.multi.metahouse.domain.entity.review.jpadto.AssetReviewJpaDto;
import com.multi.metahouse.domain.entity.review.jpadto.ProjectReviewJpaDto;
import com.multi.metahouse.order.repository.dao.OrderDAO;
import com.multi.metahouse.review.repository.dao.ReviewDAO;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewDAO reviewDAO;
	private OrderDAO orderDAO;

	@Autowired
	public ReviewServiceImpl(ReviewDAO reviewDAO, OrderDAO orderDAO) {
		super();
		this.reviewDAO = reviewDAO;
		this.orderDAO = orderDAO;
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
	//에셋 리뷰 조회(리뷰내용+이미지+답글)
	@Override
	@Transactional
	public List<AssetReviewDTO> getAllReviewsByAid(String assetId) {
		List<AssetReviewDTO> AssetReviews = reviewDAO.getAllReviewsByAsset(assetId);
		int AssetreviewId = 0;
		for (int i = 0; i < AssetReviews.size(); i++) {
			AssetreviewId = AssetReviews.get(i).getAsset_review_id();
			List<ReviewContentsDTO> reviewImg = reviewDAO.getAllReviewsImg(AssetreviewId, "a");
			if(reviewImg.size()>0){
				AssetReviews.get(i).setReviewImg(reviewImg);
			}
		}
		return AssetReviews;
	}

	//프로젝트 리뷰 조회(리뷰내용+이미지+답글)
	@Override
	@Transactional
	public List<ProjectReviewDTO> getAllReviewsByPJTid(Long projectId) {
		List<ProjectReviewDTO> PJTreviews = reviewDAO.getAllReviewsByPJT(projectId);
		int PJTreviewId = 0;
		for (int i = 0; i < PJTreviews.size(); i++) {
			PJTreviewId = PJTreviews.get(i).getProject_review_id();
			List<ReviewContentsDTO> reviewImg = reviewDAO.getAllReviewsImg(PJTreviewId, "p");
			if(reviewImg.size()>0){
				PJTreviews.get(i).setReviewImg(reviewImg);
			}
		}
				
		return PJTreviews;
	}
	/*-------------------- 민우 영역 ------------------*/

	@Override //프로젝트 리뷰저장
	public void insertProjectReview(ProjectReviewJpaDto projectReviewDto) {
//		1. reviewEntity에 자식빼고 나머지 필드에 값 저장
		ProjectReviewEntity reviewEntity = projectReviewDto.toEntity();
//		2. reviewEntity -> reviewContentsEntity필드에 값 저장
		if(projectReviewDto.getContentsList()!=null && projectReviewDto.getContentsList().size() > 0 ) {
			for(ProjectReviewJpaDto.ProjectReviewContents content : projectReviewDto.getContentsList()) {
				ProjectReviewContentsEntity contentEntity = content.toEntity();
				contentEntity.setProjectReviewId(reviewEntity);
				reviewEntity.getReviewContentsEntityList().add(contentEntity);
			}
		}
		reviewDAO.saveProjectReview(reviewEntity);
	}

	@Override //프로젝트 리뷰 개수 반환
	public Long countByOrderIdAndWriterId(Long orderId, String writerId) {
		
		return reviewDAO.countByOrderIdAndWriterId(orderId, writerId);
	}

	@Override //에셋 리뷰 저장
	public void insertAssetReview(AssetReviewJpaDto assetReviewDto) {
//		1. reviewEntity에 자식빼고 나머지 필드에 값 저장
		AssetReviewEntity reviewEntity = assetReviewDto.toEntity();
//		2. reviewEntity -> reviewContentsEntity필드에 값 저장
		if(assetReviewDto.getContentsList()!=null && assetReviewDto.getContentsList().size() > 0 ) {
			for(AssetReviewJpaDto.AssetReviewContents content : assetReviewDto.getContentsList()) {
				AssetReviewContentsEntity contentEntity = content.toEntity();
				contentEntity.setAssetReviewId(reviewEntity);
				reviewEntity.getReviewContentsEntityList().add(contentEntity);
			}
		}
		reviewDAO.saveAssetReview(reviewEntity);
		
	}

	@Override //에셋 리뷰 개수
	public Long countAssetReviewByOrderIdAndWriterId(Long orderId, String writerId) {
		return reviewDAO.countAssetReviewByOrderIdAndWriterId(orderId, writerId);
	}
}