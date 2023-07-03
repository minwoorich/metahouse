package com.multi.metahouse.order.service;

import java.util.List;

import com.multi.metahouse.domain.dto.order.AssetOrdersDTO;
import com.multi.metahouse.domain.dto.order.ProjectOrdersDTO;
import com.multi.metahouse.domain.dto.order.SelectedAddOptionDTO;
import com.multi.metahouse.domain.entity.user.User;

public interface OrderService {
	public void orderA(AssetOrdersDTO assetOrder, User loginUser, int consumeAmount);

	public void orderP(ProjectOrdersDTO projectOrder, List<SelectedAddOptionDTO> option, User loginUser,
			int consumeAmount);
}
