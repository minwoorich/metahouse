package com.multi.metahouse.review.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.review.ReviewContentsDTO;



public interface ReviewContentsDAO {
    void saveReviewContents(ReviewContentsDTO reviewContents);//리뷰 콘텐츠를 저장한다.
    void updateReviewContents(ReviewContentsDTO reviewContents);//리뷰콘텐츠를 업데이트한다.
    void deleteReviewContents(int reviewContentsId);//주어진 리뷰 콘텐츠 ID에 해당하는 리뷰 콘텐츠를 삭제한다.
    ReviewContentsDTO getReviewContentsById(int reviewContentsId);//주어진 리뷰 콘텐츠 ID에 해당하는 리뷰 콘텐츠를 가져옵니다.
    List<ReviewContentsDTO> getReviewContentsByReviewId(int reviewId);//주어진 리뷰 ID에 해당하는 모든 리뷰 콘텐츠를 가져옵니다.
}




