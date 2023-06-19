package com.multi.metahouse.order.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.order.OrderDTO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	SqlSession sqlSession;
	
	@Autowired
	public OrderDAOImpl(SqlSession sqlSession) {
		super();
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<OrderDTO> orderlist() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.multi.metahouse.order.selectOrder");
	}

	

}
