package com.multi.metahouse.order.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;

public interface ProjectOrdersRepository extends JpaRepository<ProjectOrdersEntity, Long>{
	List<ProjectOrdersEntity> findAllByBuyerId(String buyerId);
}
