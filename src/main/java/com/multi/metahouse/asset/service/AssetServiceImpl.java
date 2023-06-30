package com.multi.metahouse.asset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.asset.repository.jpa.AssetRepository;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

@Service
public class AssetServiceImpl implements AssetService {
	AssetDAO dao;

	@Autowired
	public AssetServiceImpl(AssetDAO dao) {
		super();
		this.dao = dao;
	}

	

	@Override
	@Transactional
	public int insert(AssetDTO asset, List<AssetDetailImgDTO> assetDetailImg, List<AssetContentDTO> assetContent) {
		// TODO Auto-generated method stub
		asset.setSeller_id("osm");
		dao.insert(asset);

		// lastInsertId 저장
		String selectAsset = dao.lastInsertId();

		for (int i = 0; i < assetContent.size(); i++) {
			AssetContentDTO dto = assetContent.get(i);

			dto.setAsset_id(selectAsset);
		}

		for (int i = 0; i < assetDetailImg.size(); i++) {
			AssetDetailImgDTO dto = assetDetailImg.get(i);

			dto.setAsset_id(selectAsset);
		}

		dao.attachFileInsert(assetContent);
		dao.optionalFileInsert(assetDetailImg);

		return 0;
	}

	/*-------------------------------------------------------------------------------------*/
	@Autowired
	AssetRepository repositry;

	// 에셋마켓 상품리스트 출력: 카테로리 값 받아서 출력해주기
	@Override
	public Page<AssetEntity> list(String category1, String category2, int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 16, Sort.by(Sort.Direction.DESC, "assetDate"));
		Page<AssetEntity> assetlistPage = null;
		if (category1 == null && category2 == null) {
			assetlistPage = repositry.findAll(pageRequest);
		} else if (category1 != null && category2 == null) {
			assetlistPage = repositry.findByCategory1(category1, pageRequest);
		} else {
			assetlistPage = repositry.findByCategory1AndCategory2(category1, category2, pageRequest);
		}

		return assetlistPage;
	}

	// 에셋 상품 정보+이미지+판매자 정보 조회
	@Override
	public AssetDTO assetInfo(String asset_id) {
		return dao.assetInfo(asset_id);
	}

	// 에셋 이미지
	@Override
	public List<AssetDetailImgDTO> assetImgInfo(String asset_id) {
		return dao.assetImgInfo(asset_id);
	}

	// 에셋 컨텐츠
	@Override
	public List<AssetContentDTO> assetContentInfo(String asset_id) {
		return dao.assetContentInfo(asset_id);
	}

}
