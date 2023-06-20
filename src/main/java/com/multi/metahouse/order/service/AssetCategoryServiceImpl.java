package com.multi.metahouse.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.order.AssetCategoryDTO;
import com.multi.metahouse.order.repository.dao.AssetCategoryDAO;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {
	
	AssetCategoryDAO dao;
	
	@Autowired
	public AssetCategoryServiceImpl(AssetCategoryDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<AssetCategoryDTO> assetCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}

}
