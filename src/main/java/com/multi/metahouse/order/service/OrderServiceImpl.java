package com.multi.metahouse.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.order.ProjectOrdersDetailEntity;
import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;
import com.multi.metahouse.domain.entity.order.SelectedAddOptionEntity;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersConfirmUpdateDTO;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.project.ProjectPackageTripleEntity;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.order.repository.dao.OrderDAO;
import com.multi.metahouse.order.repository.jpa.ProjectOrdersRepository;
import com.multi.metahouse.point.repository.dao.PointDAO;

@Service
public class OrderServiceImpl implements OrderService {
	OrderDAO dao;
	PointDAO pointDao;
	ProjectOrdersRepository projectOrderRepository;

	@Autowired
	public OrderServiceImpl(OrderDAO dao, PointDAO pointDao, ProjectOrdersRepository projectOrderRepository) {
		super();
		this.dao = dao;
		this.pointDao = pointDao;
		this.projectOrderRepository = projectOrderRepository;
	}


	@Override
	@Transactional
	public int orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount) {
		int payment = loginUser.getPoint()-consumeAmount;
		int result = 0;
		if(payment>=0) {
			//에셋 주문내역 생성
			dao.insertOrderA(assetOrder);
			System.out.println(dao.insertOrderA(assetOrder));
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
	public List<ProjectOrdersResponse.Response> selectOrderList(String buyerId, int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC, "orderCommitDate"));
		List<ProjectOrdersEntity> orderList = projectOrderRepository.findAllByBuyerId(pageRequest, buyerId);

		List<ProjectOrdersResponse.Response> requestOrderList = new ArrayList<>();

		for (ProjectOrdersEntity entity : orderList) {
			if ("주문대기중".equals(entity.getOrderStatus()) || "주문취소".equals(entity.getOrderStatus())) {
				requestOrderList.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity),
						getPackageType(entity), null, null));
			} else if ("진행중".equals(entity.getOrderStatus())) {
				requestOrderList.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity),
						getPackageType(entity), entity.getOrderDetail().getOrderDate(), null));
			} else {// "구매확정"

				requestOrderList
						.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity), getPackageType(entity),
								entity.getOrderDetail().getOrderDate(), entity.getOrderDetail().getCompletionDate()));
			}
		}

		return requestOrderList;
	}

	@Override
	public List<ProjectOrdersResponse.Response> selectOrderList(String buyerId, String category1, String category2,
			LocalDateTime category4, LocalDateTime category5, int pageNo) {
		List<ProjectOrdersEntity> orderList = null;

		PageRequest pageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC, "orderCommitDate"));
		if ("all".equals(category1) && !"all".equals(category1)) {
			orderList = projectOrderRepository.selectOrderForBuyerWithoutC1(pageRequest, buyerId, category2, category4,
					category5);
		} else if (!"all".equals(category1) && "all".equals(category2)) {
			orderList = projectOrderRepository.selectOrderForBuyerWithoutC2(pageRequest, buyerId, category1, category4,
					category5);
		} else if ("all".equals(category1) && "all".equals(category2)) {
			orderList = projectOrderRepository.selectOrderForBuyerWithoutC1AndC2(pageRequest, buyerId, category4,
					category5);
		} else {
			orderList = projectOrderRepository.selectOrderForBuyer(pageRequest, buyerId, category1, category2,
					category4, category5);
		}

//		 // 엔티티 잘 받아 오는지 확인
		for (ProjectOrdersEntity entity : orderList) {
			System.out.println("entity : " + entity);
			System.out.println("");
		}

		List<ProjectOrdersResponse.Response> requestOrderList = new ArrayList<>();

		for (ProjectOrdersEntity entity : orderList) {
			if ("주문대기중".equals(entity.getOrderStatus()) || "주문취소".equals(entity.getOrderStatus())) {
				requestOrderList.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity),
						getPackageType(entity), null, null));
			} else if ("진행중".equals(entity.getOrderStatus())) {
				requestOrderList.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity),
						getPackageType(entity), entity.getOrderDetail().getOrderDate(), null));
			} else {// "구매확정"
				requestOrderList
						.add(new ProjectOrdersResponse.Response(entity, getTotalPrice(entity), getPackageType(entity),
								entity.getOrderDetail().getOrderDate(), entity.getOrderDetail().getCompletionDate()));
			}
		}

		return requestOrderList;
	}

	// 구매확정 업데이트
	@Override
	@Transactional
	public void updateOrder(ProjectOrdersConfirmUpdateDTO updatedData) {

		ProjectOrdersEntity orderEntity = projectOrderRepository.findById(updatedData.getOrderId())
				.orElseThrow(() -> new RuntimeException());
		
		if("진행중".equals(updatedData.getOrderStatus())) {
			orderEntity.getOrderDetail().setCompletionDate(LocalDateTime.now());
			orderEntity.setOrderStatus("구매확정");
		}else {
			if("accept".equals(updatedData.getAcceptanceValue())){
				orderEntity.getOrderDetail().setOrderDate(LocalDateTime.now());
				orderEntity.setOrderStatus("진행중");
			}else {
				orderEntity.setOrderStatus("주문취소");
			}
		}
		
		System.out.println("orderEntity 써비스단!!!" + orderEntity);
	}

}
