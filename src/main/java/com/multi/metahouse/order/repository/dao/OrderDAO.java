package com.multi.metahouse.order.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.order.ProjectOrdersEntity;

public interface OrderDAO {
	/* ---------------------- 승언님 영역 -----------------------------*/
	public int insertOrderA(AssetOrdersDTO assetOrder);
	public int insertOrderP(ProjectOrdersDTO projectOrder);
	public int insertOrderOption(SelectedAddOptionDTO option);
	
	/* ------------------------- 민우 영역 --------------------------- */
	List<ProjectOrdersEntity> findAllProjectOrders(String buyerId);
}
