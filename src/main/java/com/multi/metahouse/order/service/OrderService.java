package com.multi.metahouse.order.service;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;

public interface OrderService {
	public int orderA(AssetOrdersDTO assetOrder);
	public int orderP(ProjectOrdersDTO projectOrder, SelectedAddOptionDTO option);
}
