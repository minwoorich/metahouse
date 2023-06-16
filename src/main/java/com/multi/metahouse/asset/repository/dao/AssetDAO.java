package com.multi.metahouse.asset.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;

public interface AssetDAO {
	//에셋 등록
	int insert(AssetDTO asset);
	
	//에셋 업데이트
	int update(AssetDTO asset);
	
	//에셋 목록보기
	List<AssetDTO> assetlist();
	
	String lastInsertId();
	
	
	int attachFileInsert(List<AssetContentDTO> attachfiledlist);
	
	int optionalFileInsert(List<AssetDetailImgDTO> optionalFiledlist);
	
	//id로 검색
	List<AssetDTO> assetlist(String asset_id);
	
	//에셋 삭제
	int delete(String asset_id);
}
