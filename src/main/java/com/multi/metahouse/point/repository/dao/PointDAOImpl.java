package com.multi.metahouse.point.repository.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.point.repository.jpa.ChargedPointInfoRepository;
import com.multi.metahouse.point.repository.jpa.ConsumedPointInfoRepository;
import com.multi.metahouse.user.repository.jpa.UserRepository;

@Repository
public class PointDAOImpl implements PointDAO {

	ChargedPointInfoRepository chargedPointInfoRepository;
	ConsumedPointInfoRepository consumedPointInfoRepository;
	UserRepository userRepository;
	
	@Autowired
	public PointDAOImpl(ChargedPointInfoRepository chargedPointInfoRepository,
			ConsumedPointInfoRepository consumedPointInfoRepository,
			UserRepository userRepository) {
		super();
		this.chargedPointInfoRepository = chargedPointInfoRepository;
		this.consumedPointInfoRepository = consumedPointInfoRepository;
		this.userRepository = userRepository;
	}
	
	// User 충전 포인트 업데이트
	@Override
	public void chargePoint(User loginUser, int chargeAmount) {
		loginUser.setPoint(loginUser.getPoint() + chargeAmount);
		userRepository.save(loginUser);
	}
	
	// User 충전 내역 업데이트
	@Override
	public void createPointInfo(User loginUser, int chargeAmount) {
		LocalDate localDate = LocalDate.now(ZoneId.of("Asia/Seoul"));
		Date date = Date.valueOf(localDate);
		
		ChargedPointInfo chargedPointInfo = new ChargedPointInfo();
		chargedPointInfo.setUserId(loginUser.getUserId());
		chargedPointInfo.setChargingPoint(chargeAmount);
		chargedPointInfo.setRemainingPoint(loginUser.getPoint() + chargeAmount);
		chargedPointInfo.setChargeDate(date);
		
		chargedPointInfoRepository.save(chargedPointInfo);
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
		System.out.println("1111111");
		List<ChargedPointInfo> cgpilist = chargedPointInfoRepository.findByUserId(loginUser.getUserId());
		System.out.println("2222222");
		List<ConsumedPointInfo> cspilist = consumedPointInfoRepository.findByUserId(loginUser.getUserId());
		System.out.println("3333333");
		int tcgp = chargedPointInfoRepository.getTotalChargingPoint(loginUser);
		System.out.println("4444444");
		int tcsp = consumedPointInfoRepository.getTotalConsumedPoint(loginUser);
		return new MyPointDTO(cgpilist, cspilist, tcgp, tcsp);
	}
}
