package com.multi.metahouse.order.repository.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.metahouse.domain.entity.asset.AssetEntity;
import com.multi.metahouse.domain.entity.order.AssetOrdersEntity;

public interface AssetOrdersRepository extends JpaRepository<AssetOrdersEntity, Long>{
	// 구매자 - 카테고리 X
	@Query(value="SELECT ord FROM AssetOrdersEntity ord INNER JOIN ord.assetId asset "
				+ " WHERE ord.buyerId2 = :buyerId2")
	List<AssetOrdersEntity> findAllByBuyerId2(Pageable pageable, @Param("buyerId2")String buyerId2);
//////////////////////////////////////////////////////////////////////////////
	//구매자 - 카테고리 O
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE ord.buyerId2 = :buyerId2 AND " + "asset.category1 = :category1 AND "
			+ "asset.category2 = :category2 AND " + "ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForBuyer( // O O O
			Pageable pageable, @Param("buyerId2") String buyerId2, @Param("category1") String category1,
			@Param("category2") String category2, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);

////////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE ord.buyerId2 = :buyerId2 AND " + 
				"asset.category2 = :category2 AND " + 
				"ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForBuyerWithoutC1( // x O O
			Pageable pageable, @Param("buyerId2") String buyerId, @Param("category2") String category2,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);

//////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE ord.buyerId2 = :buyerId2 AND " + "asset.category1 = :category1 AND "
			+ "ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForBuyerWithoutC2( // O X O
			Pageable pageable, @Param("buyerId2") String buyerId, @Param("category1") String category1,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////////

	@Query(value = "select ord FROM AssetOrdersEntity ord " + 
			"WHERE ord.buyerId2 = :buyerId2 AND "
			+ "ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForBuyerWithoutC1AndC2( // x x O
			Pageable pageable, @Param("buyerId2") String buyerId, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);
////////////////////////////////////////////////////////////////////////////////////
	
	//판매자 - 카테고리 X
	@Query(value="SELECT ord FROM AssetOrdersEntity ord INNER JOIN ord.assetId asset "
				+ " WHERE asset.sellerId = :sellerId")
	List<AssetOrdersEntity> findAllBySellerId(Pageable pageable, @Param("sellerId")String sellerId);
	
	//판매자 - 카테고리 O
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE asset.sellerId = :sellerId AND " + "asset.category1 = :category1 AND "
			+ "asset.category2 = :category2 AND " + "ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForSeller( // O O O
			Pageable pageable, @Param("sellerId") String sellerId, @Param("category1") String category1,
			@Param("category2") String category2, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);

////////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE asset.sellerId = :sellerId AND " + 
				"asset.category2 = :category2 AND " + 
				"ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForSellerWithoutC1( // x O O
			Pageable pageable, @Param("sellerId") String sellerId, @Param("category2") String category2,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);

//////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM AssetOrdersEntity ord " + "INNER JOIN ord.assetId asset "
			+ "WHERE asset.sellerId = :sellerId AND " + "asset.category1 = :category1 AND "
			+ "ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForSellerWithoutC2( // O X O
			Pageable pageable, @Param("sellerId") String sellerId, @Param("category1") String category1,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////////

	@Query(value = "select ord FROM AssetOrdersEntity ord " +  "INNER JOIN ord.assetId asset " +
				"WHERE asset.sellerId = :sellerId AND " +
				"ord.assetOrderDate BETWEEN :category4 AND :category5")
	List<AssetOrdersEntity> selectOrderForSellerWithoutC1AndC2( // x x O
			Pageable pageable, @Param("sellerId") String sellerId, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);
	
	
}
