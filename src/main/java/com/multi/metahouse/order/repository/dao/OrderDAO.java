package com.multi.metahouse.order.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.order.OrderDTO;

public interface OrderDAO {
	List<OrderDTO> orderlist();
}
