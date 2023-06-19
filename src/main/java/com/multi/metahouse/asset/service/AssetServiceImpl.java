package com.multi.metahouse.asset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.asset.repository.dao.AssetRepositry;
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
	public int update(AssetDTO asset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AssetDTO> assetlist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssetDTO> assetlist(String asset_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String asset_id) {
		// TODO Auto-generated method stub
		return 0;
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
//---------------------------------------------------------
	@Autowired
	AssetRepositry repositry;
	//에셋마켓 상품리스트 출력: 카테로리 값 받아서 출력해주기 'findAllById(카테고리 값)'
	public Page<AssetEntity> list(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 16, Sort.by(Sort.Direction.DESC,"assetDate"));
		return repositry.findAll(pageRequest);
	}
	
	public AssetEntity assetView(String assetId) {
		return repositry.findById(assetId).get();
	}
	
		
}
