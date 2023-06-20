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

	// 에셋 업데이트
	int update(AssetDTO asset);

	// 에셋 목록보기
	List<AssetDTO> assetlist();

	// id로 검색
	List<AssetDTO> assetlist(String asset_id);

	// 에셋 삭제
	int delete(String asset_id);

/*----------------------------------------------------*/
	//에셋마켓 상품 전체보기
	Page<AssetEntity> list(int pageNo);
	//에셋마켓 특정상품보기
	public AssetDTO assetInfo(String asset_id);
	public List<AssetDetailImgDTO> assetImgInfo(String asset_id);
	public List<AssetContentDTO> assetContentInfo(String asset_id);
}
