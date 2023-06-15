package com.multi.metahouse.asset.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;

@Repository
public class AssetDAOImpl implements AssetDAO {
	
	SqlSession sqlSession;
	
	@Autowired
	public AssetDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

	@Override
	public int insert(AssetDTO asset) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.metahaus.asset.insertAsset", asset);
	}

	@Override
	public List<AssetDTO> assetlist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String asset_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(AssetDTO asset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AssetDTO> assetlist(String asset_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int attachFileInsert(List<AssetContentDTO> attachfiledlist) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.metahaus.asset.insertAttachFile", attachfiledlist);
	}

	@Override
	public int optionalFileInsert(List<AssetDetailImgDTO> optionalFiledlist) {
		// TODO Auto-generated method stub
		return sqlSession.insert("com.multi.metahaus.asset.insertOptionalFile", optionalFiledlist);
	}

	@Override
	public String lastInsertId() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.metahaus.asset.selectAsset");
	}
	

}
