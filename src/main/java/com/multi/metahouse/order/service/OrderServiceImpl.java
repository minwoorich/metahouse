package com.multi.metahouse.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
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
	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> options) {
		int resultP = dao.insertOrderP(projectOrder);
		int resultO = 0;
		for (int i = 0; i < options.size(); i++) {
			options.get(i).setOrder_id(projectOrder.getOrder_id());
			if (options.get(i).getAdd_option_id() != null) {
				dao.insertOrderOption(options.get(i));
				resultO++;
			}
		}

		return resultP+resultO;

	}

}
