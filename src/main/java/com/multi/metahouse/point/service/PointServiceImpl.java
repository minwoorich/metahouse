package com.multi.metahouse.point.service;

import java.util.List;
import java.util.Map;

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
		String Method = "Metapoint";
		dao.createChargedPointInfo(loginUser, chargeAmount, Method);
		dao.chargePoint(loginUser, chargeAmount);
	}

	@Override
	public void consumePoint(User loginUser, int consumeAmount) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<ChargedPointInfo> chargePointInfoList(User loginUser) {
		return dao.chargePointInfoList(loginUser);
	}
	
	@Override
	public List<ChargedPointInfo> chargePointInfoList(User loginUser, int pageNo) {
		return dao.chargePointInfoList(loginUser, pageNo);
	}
	
	// Charge Map
	@Override
	public Map<String, Object> chargePointInfoListJSON(User loginUser, int pageNo) {
		return dao.chargePointInfoListJSON(loginUser, pageNo);
	}	

	@Override
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser) {
		return dao.consumePointInfoList(loginUser);
	}
	
	@Override
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser, int pageNo) {
		return dao.consumePointInfoList(loginUser, pageNo);
	}
	
	// Consume Map
	@Override
	public Map<String, Object> consumePointInfoListJSON(User loginUser, int pageNo) {
		return dao.consumePointInfoListJSON(loginUser, pageNo);
	}
	
	@Override
	public MyPointDTO getMyPointDTO(User loginUser) {
		return dao.getMyPointDTO(loginUser);
	}
	
}
