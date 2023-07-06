package com.multi.metahouse.review.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.metahouse.domain.entity.review.ProjectReviewEntity;

public interface ProjectReviewRepository extends JpaRepository<ProjectReviewEntity, Long>{
	Long countByProjectOrderIdAndWriterId(Long projectOrderId, String writerId );
}
