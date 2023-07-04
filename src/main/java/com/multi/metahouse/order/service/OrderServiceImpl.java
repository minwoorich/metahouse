package com.multi.metahouse.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.domain.entity.order.SelectedAddOptionEntity;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.repository.dao.OrderDAO;
import com.multi.metahouse.point.repository.dao.PointDAO;

@Service
public class OrderServiceImpl implements OrderService {
	OrderDAO dao;
	PointDAO pointDao;

	@Autowired
	public OrderServiceImpl(OrderDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	@Transactional
	public int orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount) {
		int resultA = dao.insertOrderA(assetOrder);
		//포인트 결제 내역생성
		String consumeInfo = "Asset";
		consumeInfo = consumeInfo.concat(assetOrder.getAsset_id());
		pointDao.consumePoint(loginUser, consumeAmount);
		pointDao.createConsumedPointInfo(loginUser, consumeAmount, consumeInfo);
		return resultA; 

	}

	@Override
	@Transactional
	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> options, User loginUser,
			int consumeAmount) {
		// 주문내역 생성
		int resultP = dao.insertOrderP(projectOrder);
		// 옵션내역 생성
		int resultO = 0;
		for (int i = 0; i < options.size(); i++) {
			options.get(i).setOrder_id(projectOrder.getOrder_id());
			if (options.get(i).getAdd_option_id() != null) {
				dao.insertOrderOption(options.get(i));
				resultO++;
			}
		}
		// 포인트 결제내역 생성
		String consumeInfo = "PJT";
		consumeInfo = consumeInfo.concat(projectOrder.getProject_id());
		pointDao.consumePoint(loginUser, consumeAmount);
		pointDao.createConsumedPointInfo(loginUser, consumeAmount, consumeInfo);

		return resultP + resultO;

	}

	/*---------------------- 민우 영역 ----------------------------- */

	private String getTotalPrice(ProjectOrdersEntity entity) {
		int optionPriceSum = 0;
		if (entity.getProjectId().getAddOptionEntityList() != null) {
			for (SelectedAddOptionEntity selected : entity.getSelectedOptionList()) {
				int optionPrice = selected.getAddOptionId().getAddOptionPrice();
				int optionCount = Integer.parseInt(selected.getCount());
				optionPriceSum += optionPrice * optionCount;
//				System.out.println("가격 : " + optionPrice + ", 개수 : " + optionCount);
			}
		}
//		List<Integer> optionPrice = entity.getSelectedOptionList().stream()
//		.map(selectedOption -> new SelectedAddOptionJpaDto.Response(selectedOption).getAdd_option_id().getAddOptionPrice())
//		.collect(Collectors.toList());
//		
//		List<Integer> optionCount = entity.getSelectedOptionList().stream()
//				.map(selectedOption -> new SelectedAddOptionJpaDto.Response(selectedOption).getCount())
//				.collect(Collectors.toList());
		return Integer.toString(optionPriceSum + entity.getOrderPrice());

	}

	private String getPackageType(ProjectOrdersEntity entity) {
		Object packageEntity = null;
		if ((packageEntity = entity.getProjectId().getSingleEntity()) != null) {
			return "BASIC";
		} else {
			int packagePrice = 0;
			if ((packagePrice = ((ProjectPackageTripleEntity) packageEntity).getBasicPrice()) == entity
					.getOrderPrice()) {
				return "BASIC";
			} else if ((packagePrice = ((ProjectPackageTripleEntity) packageEntity).getEconomyPrice()) == entity
					.getOrderPrice()) {
				return "ECONOMY";
			} else {
				return "PREMIUM";
			}
		}
	}

	@Override
	public List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyerByUserId(String buyerId) {
		List<ProjectOrdersEntity> orderList = dao.findAllProjectOrders(buyerId);
//		 // 엔티티 잘 받아 오는지 확인
		for(ProjectOrdersEntity entity : orderList) {
			System.out.println("entity : " + entity);
			System.out.println("");
		}

		List<ProjectOrdersResponse.BuyerResponse> requestOrderList = new ArrayList<>();
	
		for (ProjectOrdersEntity entity : orderList) {
			if("주문대기중".equals(entity.getOrderStatus()) || "주문취소".equals(entity.getOrderStatus())) {
				requestOrderList.add(
						new ProjectOrdersResponse.BuyerResponse(
								entity, 
								getTotalPrice(entity), 
								getPackageType(entity),
								null,
								null));
			}else if("진행중".equals(entity.getOrderStatus())){
				requestOrderList.add(
						new ProjectOrdersResponse.BuyerResponse(
								entity, 
								getTotalPrice(entity), 
								getPackageType(entity),
								entity.getOrderDetail().getOrderDate(),
								null));
			}else {//"구매확정"
				System.out.println("------------------");
				System.out.println("주문완료일시 : " + entity.getOrderDetail().getCompletionDate());
				requestOrderList.add(
						new ProjectOrdersResponse.BuyerResponse(
								entity, 
								getTotalPrice(entity), 
								getPackageType(entity),
								entity.getOrderDetail().getOrderDate(),
								entity.getOrderDetail().getCompletionDate()));
			}
			
		}
		// 엔티티에서 DTO로 잘 데이터 넘겼는지 확인
		for (ProjectOrdersResponse.BuyerResponse resp : requestOrderList) {
			System.out.println("resp : " + resp);
		}

		
		return requestOrderList;
	}

	@Override
	public List<ProjectOrdersResponse.SellerResponse> selectOrderListForSellerByUserId(String sellerId) {
		// TODO Auto-generated method stub
		return null;
	}


}
