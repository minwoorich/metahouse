package com.multi.metahouse.asset.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.asset.repository.jpa.AssetRepository;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

@Repository
public class AssetDAOImpl implements AssetDAO {

	SqlSession sqlSession;
	AssetRepository repository;

	@Autowired
	public AssetDAOImpl(SqlSession sqlSession, AssetRepository repository) {
		super();
		this.sqlSession = sqlSession;
		this.repository = repository;
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
	
/*------------------------------------------ OSE ---------------------------------------------*/

	//에셋 상품 정보+판매자 정보 조회
	@Override
	public AssetDTO assetInfo(String asset_id) {
		return sqlSession.selectOne("com.multi.metahaus.asset.AssetInfo",asset_id);
	}
	//에셋 상품 이미지 조회
	public List<AssetDetailImgDTO> assetImgInfo (String asset_id){
		return sqlSession.selectList("com.multi.metahaus.asset.AssetImgs",asset_id);
		
	}
	//에셋 상품 컨텐츠
	public List<AssetContentDTO> assetContentInfo(String asset_id){
		return sqlSession.selectList("com.multi.metahaus.asset.AssetContents",asset_id);
		
	}

	/////////////////////////LCH////////////////////////

	@Override
	public List<AssetDTO> findTopNByAssetReviewAvg(int limit) {
		return sqlSession.selectList("com.multi.metahaus.asset.findOrderByReviewRating",limit);
	}
	
	
	/*--------------------------------------- YSH --------------------------------------------- */
	@Override
	public List<AssetDTO> findBySellerId(String sellerId) {
		return sqlSession.selectList("com.multi.metahaus.asset.findAssetInfo", sellerId);
	}
	
}
