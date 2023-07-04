package com.multi.metahouse.order.repository.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectOrdersResponse;

public interface ProjectOrdersRepository extends JpaRepository<ProjectOrdersEntity, Long>{
	
	
	List<ProjectOrdersEntity> findAllByBuyerId(Pageable pageable, String buyerId);
	
	@Query(value = "select ord FROM ProjectOrdersEntity ord "+
				"INNER JOIN ord.projectId project " + 
				"WHERE ord.buyerId = :buyerId AND " +
					"project.category1 = :category1 AND " + 
					"project.category2Pj = :category2 AND " + 
					"ord.orderPrice = :category3 AND " +
					"ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForBuyer( // O O O
			Pageable pageable,
			@Param("buyerId")String buyerId,
			@Param("category1")String category1, 
			@Param("category2")String category2,
			@Param("category3")String category3,
			@Param("category4")LocalDateTime category4,
			@Param("category5")LocalDateTime category5);
////////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE ord.buyerId = :buyerId AND " +
				"project.category2Pj = :category2 AND " + 
				"ord.orderPrice = :category3 AND " +
				"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1( // x O O
		Pageable pageable,
		@Param("buyerId")String buyerId,
		@Param("category2")String category2,
		@Param("category3")String category3,
		@Param("category4")LocalDateTime category4,
		@Param("category5")LocalDateTime category5);

//////////////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"project.category1 = :category1 AND " + 
			"ord.orderPrice = :category3 AND " +
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC2( // O X O
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category1")String category1, 
	@Param("category3")String category3,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"project.category1 = :category1 AND " + 
			"project.category2Pj = :category2 AND " + 
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC3( // O O X
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category1")String category1, 
	@Param("category2")String category2,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
/////////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"ord.orderPrice = :category3 AND " +
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1AndC2( // x x O
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category3")String category3,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"project.category2Pj = :category2 AND " + 
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1AndC3( // x O x
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category2")String category2,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
//////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"project.category1 = :category1 AND " + 
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC2AndC3( // O x x
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category1")String category1, 
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////
@Query(value = "select ord FROM ProjectOrdersEntity ord "+
		"INNER JOIN ord.projectId project " + 
		"WHERE ord.buyerId = :buyerId AND " +
			"ord.orderCommitDate BETWEEN :category4 AND :category5")
List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1AndC2AndC3( // x x x
	Pageable pageable,
	@Param("buyerId")String buyerId,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
}
