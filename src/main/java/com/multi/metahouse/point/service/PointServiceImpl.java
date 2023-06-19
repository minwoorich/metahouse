package com.multi.metahouse.point.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.point.repository.dao.PointDAO;

@Service
public class PointServiceImpl implements PointService {

	PointDAO dao;
	
	@Autowired
	public PointServiceImpl(PointDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	@Transactional
	public void chargePoint(User loginUser, int chargeAmount) {
		dao.createPointInfo(loginUser, chargeAmount);
		dao.chargePoint(loginUser, chargeAmount);
	}

	@Override
	public void consumePoint(User loginUser, int consumeAmount) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ChargedPointInfo> chargePointInfoList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConsumedPointInfo> consumePointInfoList(String userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public MyPointDTO getMyPointDTO(User loginUser) {
		return dao.getMyPointDTO(loginUser);
	}
	
}
