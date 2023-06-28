package com.multi.metahouse.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
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
	public int orderA(AssetOrdersDTO assetOrder) {
		return dao.insertOrderA(assetOrder);

	}
	@Override
	@Transactional
	public int orderP(ProjectOrdersDTO projectOrder, SelectedAddOptionDTO option) {
		dao.insertOrderP(projectOrder);
		option.setOrder_id(projectOrder.getOrder_id());
		System.out.println(option);
		if(option.getAdd_option_id() != null) {
			dao.insertOrderOption(option);
		}
		
		return 0;
		
	}

}
