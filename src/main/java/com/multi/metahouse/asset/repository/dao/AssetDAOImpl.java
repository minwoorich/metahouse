package com.multi.metahouse.asset.repository.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.asset.repository.jpa.AssetContentRepository;
import com.multi.metahouse.asset.repository.jpa.AssetDetailImgRepository;
import com.multi.metahouse.asset.repository.jpa.AssetRepository;
import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.dto.asset.AssetDetailImgDTO;
import com.multi.metahouse.domain.entity.asset.AssetContentEntity;
import com.multi.metahouse.domain.entity.asset.AssetDetailImgEntity;
import com.multi.metahouse.domain.entity.asset.AssetEntity;

@Repository
public class AssetDAOImpl implements AssetDAO {

	SqlSession sqlSession;
	AssetRepository repository;
	AssetContentRepository contentRepository;
	AssetDetailImgRepository detailImgRepository;

	@Autowired
	public AssetDAOImpl(SqlSession sqlSession, AssetRepository repository, AssetContentRepository contentRepository,
			AssetDetailImgRepository detailImgRepository) {
		super();
		this.sqlSession = sqlSession;
		this.repository = repository;
		this.contentRepository = contentRepository;
		this.detailImgRepository = detailImgRepository;
	}

	@Override
	public void insert(AssetDetailImgEntity assetDetailImgEntity) {
		detailImgRepository.save(assetDetailImgEntity);
	}

	@Override
	public void insert(AssetContentEntity assetContentEntity) {
		contentRepository.save(assetContentEntity);
	}
	
	@Override
	public List<AssetEntity> selectAssetListBySellerId(String sellerId) {
		return repository.findBySellerId(sellerId);
	}
	
	@Override
	public void deleteAssetByAssetId(String assetId) {
		repository.deleteById(assetId);
	}
	
	@Override
	public void deleteAssetContentByAssetId(String assetId) {
		AssetEntity assetEntity = repository.findById(assetId).orElseThrow(() -> new RuntimeException());
		contentRepository.deleteByAssetId(assetEntity);
	}

	@Override
	public void deleteAssetImgByAssetId(String assetId) {
		AssetEntity assetEntity = repository.findById(assetId).orElseThrow(() -> new RuntimeException());
		detailImgRepository.deleteByAssetId(assetEntity);
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
}
