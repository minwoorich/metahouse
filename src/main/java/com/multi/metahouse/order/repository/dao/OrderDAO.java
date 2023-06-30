package com.multi.metahouse.order.repository.dao;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;

public interface OrderDAO {
	public int insertOrderA(AssetOrdersDTO assetOrder);
	public int insertOrderP(ProjectOrdersDTO projectOrder);
	public int insertOrderOption(SelectedAddOptionDTO option);
}
