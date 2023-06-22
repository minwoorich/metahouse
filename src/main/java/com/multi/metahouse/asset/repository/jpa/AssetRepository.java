package com.multi.metahouse.asset.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;


@Repository
public interface AssetRepository extends JpaRepository<AssetEntity, String>{
	Page<AssetEntity> findByCategory1(@Param("category1")String category1, PageRequest pageRequest);
	Page<AssetEntity> findByCategory1AndCategory2(@Param("category1")String category1, @Param("category2")String category2, PageRequest pageRequest);
}
