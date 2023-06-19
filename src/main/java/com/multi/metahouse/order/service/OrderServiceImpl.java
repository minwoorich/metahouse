package com.multi.metahouse.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.order.OrderDTO;
import com.multi.metahouse.order.repository.dao.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {
	
	OrderDAO dao;
	
	@Autowired
	public OrderServiceImpl(OrderDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public List<OrderDTO> orderlist() {
		// TODO Auto-generated method stub
		return dao.orderlist();
	}

	
}
