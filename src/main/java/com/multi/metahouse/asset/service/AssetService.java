package com.multi.metahouse.asset.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.dto.asset.AssetFormDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

public interface AssetService {
	// 에셋 등록
	void insert(String storeAttachFileName, String storeThumbnailFileName, List<AssetDetailImgDTO> storeOptionalFileNameList, AssetFormDTO assetFormDto);
	
	//나의 에셋 전체보기
	List<AssetDTO> selectAssetListBySellerId(String sellerId, int pageNo);
	
	//에셋 삭제
	void deleteAssetByAssetId(String assetId);
/*----------------------------------------------------*/
	//에셋마켓 상품 전체보기
	public List<AssetDTO>list(Integer currnetPage, String category, String category2, String sort);
	//에셋마켓 특정상품보기
	public AssetDTO assetInfo(String asset_id);
	public List<AssetDetailImgDTO> assetImgInfo(String asset_id);
	public List<AssetContentDTO> assetContentInfo(String asset_id);
}
