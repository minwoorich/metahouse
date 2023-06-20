package com.multi.metahouse.order.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.order.AssetCategoryDTO;

@Repository
public class AssetCategoryDAOImpl implements AssetCategoryDAO {
	
	SqlSession sqlSession;
	
	@Autowired
	public AssetCategoryDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}



	@Override
	public List<AssetCategoryDTO> assetCategoryList() {
		// TODO Auto-generated method stub
		return null;
	}

}
