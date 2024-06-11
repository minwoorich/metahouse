package com.multi.metahouse.asset.repository.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.asset.AssetDetailImgEntity;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

public interface AssetDetailImgRepository extends JpaRepository<AssetDetailImgEntity, String> {
	void deleteByAssetId(AssetEntity assetId);
}
