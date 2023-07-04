package com.multi.metahouse.order.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.order.repository.jpa.ProjectOrdersRepository;

@Repository
public class OrderDAOImpl implements OrderDAO {
	SqlSession sqlSession;
	ProjectOrdersRepository projectOrderRepository;

	@Autowired
	public OrderDAOImpl(SqlSession sqlSession, ProjectOrdersRepository projectOrderRepository) {
		super();
		this.sqlSession = sqlSession;
		this.projectOrderRepository = projectOrderRepository;
	}

	/* ----------------------------- 승언님 영역 ------------------------------------- */
	@Override
	public int insertOrderA(AssetOrdersDTO assetOrder) {
		return sqlSession.insert("com.multi.metahouse.order.insertOrderA", assetOrder);
	}
	@Override
	public int insertOrderP(ProjectOrdersDTO projectOrder) {
		return sqlSession.insert("com.multi.metahouse.order.insertOrderP", projectOrder);
	}
	@Override
	public int insertOrderOption(SelectedAddOptionDTO option) {
		return sqlSession.insert("com.multi.metahouse.order.insertOrderOption", option);
	}

	

	/* ---------------------------------- 민우 영역 ------------------------------------- */
	@Override
	public List<ProjectOrdersEntity> findAllProjectOrders(String buyerId) {
		return projectOrderRepository.findAllByBuyerId(buyerId);
	}
}
