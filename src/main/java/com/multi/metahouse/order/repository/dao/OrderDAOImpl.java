package com.multi.metahouse.order.repository.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	SqlSession sqlSession;

	@Autowired
	public OrderDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}

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

}
