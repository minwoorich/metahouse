package com.multi.metahouse.asset.repository.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetContentEntity;
import com.multi.metahouse.domain.entity.asset.AssetDetailImgEntity;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

public interface AssetDAO {
	//에셋 등록
	void insert(AssetDetailImgEntity assetDetailImgEntity);
	void insert(AssetContentEntity assetContentEntity);
	
	//에셋 불러오기(sellerId)
	List<AssetEntity> selectAssetListBySellerId(Pageable pageable , String sellerId);
	
	//에셋 삭제
	void deleteAssetByAssetId(String assetId);
	void deleteAssetContentByAssetId(String assetId);
	void deleteAssetImgByAssetId(String assetId);
/*------------------------------------- OSE ------------------------------------------*/
	//에셋 조회하기
	public List<AssetDTO> Allasset(Map<String, Object> condition);
	public Map<String, Integer> assetReviewSummary(String assetId);
	//에셋 상품 정보+판매자 정보 조회
	public AssetDTO assetInfo(String asset_id);
	//에셋 상품 이미지 조회
	public List<AssetDetailImgDTO> assetImgInfo (String asset_id);
	//에셋 상품 컨텐츠
	public List<AssetContentDTO> assetContentInfo(String asset_id);
	
	/////////LCH/////////////////////
	public List<AssetDTO> findTopNByAssetReviewAvg(int limit);
	
	/*-------------------------------- YSH --------------------------------------*/
	public List<AssetDTO> findBySellerId(String sellerId);
}
