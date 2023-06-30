package com.multi.metahouse.asset.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

public interface AssetService {
	// 에셋 등록
	int insert(AssetDTO asset, List<AssetDetailImgDTO> assetDetailImg, List<AssetContentDTO> assetContent);

	

/*----------------------------------------------------*/
	//에셋마켓 상품 전체보기
	public Page<AssetEntity> list(String category1, String category2, int pageNo);
	//에셋마켓 특정상품보기
	public AssetDTO assetInfo(String asset_id);
	public List<AssetDetailImgDTO> assetImgInfo(String asset_id);
	public List<AssetContentDTO> assetContentInfo(String asset_id);
}
