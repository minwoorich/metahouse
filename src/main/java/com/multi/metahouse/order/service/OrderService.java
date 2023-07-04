package com.multi.metahouse.order.service;

import java.time.LocalDateTime;
import java.util.List;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersConfirmUpdateDTO;
import com.multi.metahouse.domain.entity.order.dtoforjpa.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.user.User;
public interface OrderService {
	/* ----------------- 승언님--------------------- */
	public int orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount);

	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> option, User loginUser,
			int consumeAmount);
	
	/* ------------------- 민우-------------- */
	// 페이징 처리된 주문 불러오기 (구매자화면)
	List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyer(
			String buyerId, int pageNo);
	// 카테고리별 주문 불러오기 (구매자 화면)
	List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyer(
			String buyerId, 
			String category1, 
			String category2, 
			LocalDateTime category4, 
			LocalDateTime category5, 
			int pageNo);

	// 페이징 처리된 주문 불러오기 (판매자 화면)
	List<ProjectOrdersResponse.SellerResponse> selectOrderListForSellerByUserId(String sellerId, int pageNo);
	// 구매확정 누르면 해당 주문에 대한 DB 업데이트
	void updateOrder(ProjectOrdersConfirmUpdateDTO updatedData);
}
