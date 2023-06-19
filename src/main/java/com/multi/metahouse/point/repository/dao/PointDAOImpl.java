package com.multi.metahouse.point.repository.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.point.repository.jpa.ChargedPointInfoRepository;
import com.multi.metahouse.point.repository.jpa.ConsumedPointInfoRepository;

@Repository
public class PointDAOImpl implements PointDAO {

	ChargedPointInfoRepository chargedPointInfoRepository;
	ConsumedPointInfoRepository consumedPointInfoRepository;
	
	@Autowired
	public PointDAOImpl(ChargedPointInfoRepository chargedPointInfoRepository,
			ConsumedPointInfoRepository consumedPointInfoRepository) {
		super();
		this.chargedPointInfoRepository = chargedPointInfoRepository;
		this.consumedPointInfoRepository = consumedPointInfoRepository;
	}

	@Override
	@Transactional
	public void chargePoint(int chargeAmount) {
//		chargedPointInfoRepository.save(new ChargedPointInfo(null, ))
	}

	@Override
	public void consumePoint(int consumeAmount) {
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

}
