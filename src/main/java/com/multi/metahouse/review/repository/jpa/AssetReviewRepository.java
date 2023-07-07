package com.multi.metahouse.review.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.review.AssetReviewEntity;

public interface AssetReviewRepository extends JpaRepository<AssetReviewEntity, Long>{
	Long countByAssetOrderIdAndWriterId(Long assetOrderId, String writerId );
}
