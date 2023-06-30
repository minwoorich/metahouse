package com.multi.metahouse.asset.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.asset.AssetContentEntity;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

public interface AssetContentRepository extends JpaRepository<AssetContentEntity, String>{
	void deleteByAssetId(AssetEntity assetId);
}
