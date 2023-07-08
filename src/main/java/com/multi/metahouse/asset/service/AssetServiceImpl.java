package com.multi.metahouse.asset.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.multi.metahouse.domain.dto.asset.AssetFormDTO;
import com.multi.metahouse.domain.dto.project.ProjectDTO;
import com.multi.metahouse.domain.entity.asset.AssetContentEntity;
import com.multi.metahouse.domain.entity.asset.AssetDetailImgEntity;
import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.user.User;

@Service
public class AssetServiceImpl implements AssetService {
	AssetDAO dao;

	@Autowired
	public AssetServiceImpl(AssetDAO dao) {
		super();
		this.dao = dao;
	}

	@Transactional
	@Override
	public void insert(String storeAttachFileName, String storeThumbnailFileName, List<AssetDetailImgDTO> storeOptionalFileNameList,
			AssetFormDTO assetFormDto) {
		AssetEntity assetEntity = new AssetEntity();
		
		//부모 Entity에 데이터 주입(AssetEntity)
		assetEntity.setSellerId(assetFormDto.getUser_id());
		assetEntity.setTitle(assetFormDto.getTitle());
		assetEntity.setCategory1(assetFormDto.getCategory1());
		assetEntity.setCategory2(assetFormDto.getCategory2_as());
		assetEntity.setDescription(assetFormDto.getDescription());
		assetEntity.setPrice(Integer.parseInt(assetFormDto.getPrice()));
		assetEntity.setMainImg(storeThumbnailFileName);
		
		//자식 Entity에 데이터 주입 (AssetContentEntity)
		AssetContentEntity assetContentEntity = new AssetContentEntity();
		assetContentEntity.setAssetStoreFilename(storeAttachFileName);
		assetContentEntity.setAssetId(assetEntity);
		//assetEntity.setAssetContentEntity(assetContentEntity);
		assetContentEntity.setAssetFileno("1");
		dao.insert(assetContentEntity);
		
		
		//자식 Entity에 데이터 주입2 (AssetDetailImgEntity	)
		if (storeOptionalFileNameList != null) {
			for (AssetDetailImgDTO dto : storeOptionalFileNameList) {
				AssetDetailImgEntity entity = new AssetDetailImgEntity();
				entity.setAssetDetailImgStoreFilename(dto.getAsset_detail_img_store_filename());
				entity.setAssetFileno(dto.getAsset_fileno());
				entity.setAssetId(assetEntity);
//				assetEntity.getAssetDetailImgEntityList().add(entity);
				
				dao.insert(entity);
			}
		}
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println("-------" + assetEntity);
		
	}
	
	@Override
	public List<AssetDTO> selectAssetListBySellerId(String sellerId, int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC, "assetDate"));
		List<AssetEntity> entityList =  dao.selectAssetListBySellerId(pageRequest, sellerId);
		List<AssetDTO> dtoList = new ArrayList<>();
		
		for(AssetEntity entity : entityList) {
			AssetDTO dto = new AssetDTO();
			dto.setAsset_id(entity.getAssetId());
			dto.setSeller_id(entity.getSellerId());
			dto.setTitle(entity.getTitle());
			dto.setCategory1(entity.getCategory1());
			dto.setCategory2_as(entity.getCategory2());
			dto.setDescription(entity.getDescription());
			dto.setPrice(entity.getPrice());
			dto.setMain_img(entity.getMainImg());
			dto.setAsset_date(entity.getAssetDate());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}
	
	@Transactional
	@Override
	public void deleteAssetByAssetId(String assetId) {
		dao.deleteAssetContentByAssetId(assetId);
		dao.deleteAssetImgByAssetId(assetId);
		dao.deleteAssetByAssetId(assetId);
	}
	
	


	/*------------------------------------------- OSE ------------------------------------------*/
	// 에셋마켓 상품리스트 출력: 카테로리 값 받아서 출력해주기
	@Override
	public List<AssetDTO> list(Integer currnetPage, String category, String category2, String sort) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("skip", currnetPage);
		condition.put("category1", category);
		condition.put("category2_as", category2);
		condition.put("sort", sort);
		System.out.println(condition);
		List<AssetDTO> assetsInPage = (List<AssetDTO>) dao.Allasset(condition);
		if(currnetPage != null) {
			for (int i = 0; i < assetsInPage.size(); i++) {
				String assetId = assetsInPage.get(i).getAsset_id();
				Map<String, Integer> reviewSummary = dao.assetReviewSummary(assetId);
				/* java.lang.Long cannot be cast to java.lang.Integer 에러 발생 
				 * : long 형(db 기준 number)을 바로 int로 변환하려고 하기 때문 */
				int reviewCount = Integer.parseInt(String.valueOf(reviewSummary.get("Review_count")));
				double reviewAvg = 0;
				if (reviewCount > 0) {
					reviewAvg = Double.parseDouble(String.valueOf(reviewSummary.get("Review_Avg")));
				}
				assetsInPage.get(i).setReview_count(reviewCount);
				assetsInPage.get(i).setAverage_reviews(reviewAvg);
			}
			
			if(sort=="popularity") {
				Comparator<AssetDTO> cp = new Comparator<AssetDTO>() {

					@Override
					public int compare(AssetDTO o1, AssetDTO o2) {
						int a = o1.getReview_count();
						int b = o2.getReview_count();
						return 1;
					}
				};
				Collections.sort(assetsInPage, cp);
			}
		}
		return assetsInPage;
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
