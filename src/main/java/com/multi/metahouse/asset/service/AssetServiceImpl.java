package com.multi.metahouse.asset.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.asset.repository.dao.AssetDAO;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;

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
		
		//lastInsertId 저장
		String selectAsset = dao.lastInsertId();
		
		for (int i = 0; i < assetContent.size(); i ++) {
			AssetContentDTO dto = assetContent.get(i);
			
			dto.setAsset_id(selectAsset);
		}
		
		for (int i = 0; i < assetDetailImg.size(); i ++) {
			AssetDetailImgDTO dto = assetDetailImg.get(i);
			
			dto.setAsset_id(selectAsset);
		}
		
		dao.attachFileInsert(assetContent);
		dao.optionalFileInsert(assetDetailImg);
		
		
		
		return 0;
	}

}
