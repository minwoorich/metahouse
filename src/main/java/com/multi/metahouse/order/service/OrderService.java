package com.multi.metahouse.order.service;

import java.time.LocalDateTime;
import java.util.List;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.project.jpadto.ProjectOrdersResponse;
import com.multi.metahouse.domain.entity.user.User;
public interface OrderService {
	/* ----------------- 승언님--------------------- */
	public int orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount);

	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> option, User loginUser,
			int consumeAmount);
	
	/* ------------------- 민우-------------- */
//	List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyerByUserId(String buyerId);
	List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyer(
			String buyerId, int pageNo);
	List<ProjectOrdersResponse.BuyerResponse> selectOrderListForBuyer(
			String buyerId, 
			String category1, 
			String category2, 
			String category3,
			LocalDateTime category4, 
			LocalDateTime category5, 
			int pageNo);
//	List<ProjectOrdersResponse.SellerResponse> selectOrderListForSellerByUserId(String sellerId);
	List<ProjectOrdersResponse.SellerResponse> selectOrderListForSellerByUserId(String sellerId, int pageNo);
}
