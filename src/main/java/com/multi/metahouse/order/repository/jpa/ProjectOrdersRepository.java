package com.multi.metahouse.order.repository.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;

public interface ProjectOrdersRepository extends JpaRepository<ProjectOrdersEntity, Long> {
///////////////////////구매자용//////////////////////////////
	List<ProjectOrdersEntity> findAllByBuyerId(Pageable pageable, String buyerId);

	@Query(value = "select ord FROM ProjectOrdersEntity ord " + "INNER JOIN ord.projectId project "
			+ "WHERE ord.buyerId = :buyerId AND " + "project.category1 = :category1 AND "
			+ "project.category2Pj = :category2 AND " + "ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForBuyer( // O O O
			Pageable pageable, @Param("buyerId") String buyerId, @Param("category1") String category1,
			@Param("category2") String category2, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);

////////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM ProjectOrdersEntity ord " + "INNER JOIN ord.projectId project "
			+ "WHERE ord.buyerId = :buyerId AND " + "project.category2Pj = :category2 AND "
			+ "ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1( // x O O
			Pageable pageable, @Param("buyerId") String buyerId, @Param("category2") String category2,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);

//////////////////////////////////////////////////////////////////////////////
	@Query(value = "select ord FROM ProjectOrdersEntity ord " + "INNER JOIN ord.projectId project "
			+ "WHERE ord.buyerId = :buyerId AND " + "project.category1 = :category1 AND "
			+ "ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForBuyerWithoutC2( // O X O
			Pageable pageable, @Param("buyerId") String buyerId, @Param("category1") String category1,
			@Param("category4") LocalDateTime category4, @Param("category5") LocalDateTime category5);
//////////////////////////////////////////////////////////////////////////////

	@Query(value = "select ord FROM ProjectOrdersEntity ord " + 
			"WHERE ord.buyerId = :buyerId AND "
			+ "ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForBuyerWithoutC1AndC2( // x x O
			Pageable pageable, @Param("buyerId") String buyerId, @Param("category4") LocalDateTime category4,
			@Param("category5") LocalDateTime category5);

///////////////////////판매자용//////////////////////////////

	@Query(value = "SELECT ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE project.creatorId = :creatorId")
	List<ProjectOrdersEntity> findAllByCreatorId(Pageable pageable, @Param("creatorId")String creatorId);
	
	@Query(value = "SELECT ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE project.creatorId = :creatorId AND "+
				"project.category1 = :category1 AND " + 
				"project.category2Pj = :category2 AND " + 
				"ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForSeller( // O O O
		Pageable pageable,
		@Param("creatorId")String creatorId,
		@Param("category1")String category1, 
		@Param("category2")String category2,
		@Param("category4")LocalDateTime category4,
		@Param("category5")LocalDateTime category5);
	
	////////////////////////////////////////////////////////////////////////////////
	
	
	@Query(value = "SELECT ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE project.creatorId = :creatorId AND "+
				"project.category2Pj = :category2 AND " + 
				"ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForSellerWithoutC1( // x O O
	Pageable pageable,
	@Param("creatorId")String creatorId,
	@Param("category2")String category2,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
	
	//////////////////////////////////////////////////////////////////////////////
	@Query(value = "SELECT ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE project.creatorId = :creatorId AND "+
				"project.category1 = :category1 AND " + 
				"ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForSellerWithoutC2( // O X O
										Pageable pageable,
										@Param("creatorId")String creatorId,
										@Param("category1")String category1, 
										@Param("category4")LocalDateTime category4,
										@Param("category5")LocalDateTime category5);
	//////////////////////////////////////////////////////////////////////////////
	
	@Query(value = "SELECT ord FROM ProjectOrdersEntity ord "+
			"INNER JOIN ord.projectId project " + 
			"WHERE project.creatorId = :creatorId AND "+
				"ord.orderCommitDate BETWEEN :category4 AND :category5")
	List<ProjectOrdersEntity> selectOrderForSellerWithoutC1AndC2( // x x O
	Pageable pageable,
	@Param("creatorId")String creatorId,
	@Param("category4")LocalDateTime category4,
	@Param("category5")LocalDateTime category5);
}