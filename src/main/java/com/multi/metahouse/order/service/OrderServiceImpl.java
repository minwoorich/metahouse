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
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.repository.dao.OrderDAO;
import com.multi.metahouse.point.repository.dao.PointDAO;

@Service
public class OrderServiceImpl implements OrderService {
	OrderDAO dao;
	PointDAO pointDao;

	@Autowired
	public OrderServiceImpl(OrderDAO dao, PointDAO pointDao) {
		super();
		this.dao = dao;
		this.pointDao = pointDao;
	}

	@Override
	@Transactional
	public int orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount) {
		int payment = loginUser.getPoint()-consumeAmount;
		int result = 0;
		if(payment>=0) {
			//에셋 주문내역 생성
			dao.insertOrderA(assetOrder);
			// 포인트 결제 내역생성
			String consumeInfo = "Asset";
			consumeInfo = consumeInfo.concat(assetOrder.getAsset_id());
			pointDao.createConsumedPointInfo(loginUser, consumeAmount, consumeInfo);
			pointDao.consumePoint(loginUser, consumeAmount);
			
			result = 1;
		}

		return result;
	}

	@Override
	@Transactional
	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> options, User loginUser,
			int consumeAmount) {
		int payment = loginUser.getPoint()-consumeAmount;
		int result = 0;
		if(payment>=0) {
			// 주문내역 생성
			dao.insertOrderP(projectOrder);
			// 옵션내역 생성
			for (int i = 0; i < options.size(); i++) {
				options.get(i).setOrder_id(projectOrder.getOrder_id());
				if (options.get(i).getAdd_option_id() != null) {
					dao.insertOrderOption(options.get(i));
				}
			}
	
			// 포인트 결제내역 생성
			String consumeInfo = "PJT";
			consumeInfo = consumeInfo.concat(projectOrder.getProject_id());
			pointDao.createConsumedPointInfo(loginUser, consumeAmount, consumeInfo);
			pointDao.consumePoint(loginUser, consumeAmount);
			
			result = 1;
		}
		
		return result;
	}

}
