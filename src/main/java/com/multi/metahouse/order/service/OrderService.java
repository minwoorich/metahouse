package com.multi.metahouse.order.service;

import java.util.List;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.user.User;

public interface OrderService {
	public int orderA(AssetOrdersDTO assetOrder);

	public int orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> option, User loginUser,
			int consumeAmount);
}
