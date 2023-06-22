package com.multi.metahouse.point.repository.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
		chargedPointInfo.setPaymentMethod("metapoint");
		chargedPointInfo.setRemainingPoint(loginUser.getPoint() + chargeAmount);
		chargedPointInfo.setChargeDate(date);
		
		chargedPointInfoRepository.save(chargedPointInfo);
	}

	@Override
	public void consumePoint(User loginUser, int consumeAmount) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ChargedPointInfo> chargePointInfoList(User loginUser) {
		PageRequest cgpiPageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC,"chargePointInfoId"));
//		Page<ChargedPointInfo> cgpiPage = chargedPointInfoRepository.findAll(cgpiPageRequest);
		Page<ChargedPointInfo> cgpiPage = chargedPointInfoRepository.findByUserId(loginUser.getUserId(), cgpiPageRequest);
		return cgpiPage.getContent();
	}
	
	@Override
	public List<ChargedPointInfo> chargePointInfoList(User loginUser, int pageNo) {
		PageRequest cgpiPageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC,"chargePointInfoId"));
		Page<ChargedPointInfo> cgpiPage = chargedPointInfoRepository.findByUserId(loginUser.getUserId(), cgpiPageRequest);
		return cgpiPage.getContent();
	}
	
	// test
	@Override
	public Map<String, Object> chargePointInfoListTest(User loginUser, int pageNo) {
		Map<String, Object> ret = new HashMap<String, Object>();
		PageRequest cgpiPageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC,"chargePointInfoId"));
		Page<ChargedPointInfo> cgpiPage = chargedPointInfoRepository.findByUserId(loginUser.getUserId(), cgpiPageRequest);
		ret.put("cgpi", cgpiPage.getContent());
		ret.put("tot_page_cgpi", cgpiPage.getTotalPages());
		return ret;
	}

	@Override
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser) {
		PageRequest cspiPageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC,"consumePointInfoId"));
		Page<ConsumedPointInfo> cspiPage = consumedPointInfoRepository.findByUserId(loginUser.getUserId(), cspiPageRequest);
		return cspiPage.getContent();
	}
	
	@Override
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser, int pageNo) {
		PageRequest cspiPageRequest = PageRequest.of(pageNo, 5, Sort.by(Sort.Direction.DESC,"consumePointInfoId"));
		Page<ConsumedPointInfo> cspiPage = consumedPointInfoRepository.findByUserId(loginUser.getUserId(), cspiPageRequest);
		return cspiPage.getContent();
	}

	@Override
	public MyPointDTO getMyPointDTO(User loginUser) {
		PageRequest cgpiPageRequest = getPageRequest("chargePointInfoId");
		Page<ChargedPointInfo> cgpiPage = chargedPointInfoRepository.findByUserId(loginUser.getUserId(), cgpiPageRequest);
		List<ChargedPointInfo> cgpilist = chargePointInfoList(loginUser);
		
		PageRequest cspiPageRequest = getPageRequest("consumePointInfoId");
		Page<ConsumedPointInfo> cspiPage = consumedPointInfoRepository.findByUserId(loginUser.getUserId(), cspiPageRequest);
		List<ConsumedPointInfo> cspilist = consumePointInfoList(loginUser);
		
		int tcgp = chargedPointInfoRepository.getTotalChargingPoint(loginUser);
		int tcsp = consumedPointInfoRepository.getTotalConsumedPoint(loginUser);
		return new MyPointDTO(cgpilist, cspilist, tcgp, tcsp, cgpiPage.getTotalPages(), cspiPage.getTotalPages());
	}
	
	@Override
	public PageRequest getPageRequest(String id) {
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, id));
		return pageRequest;
	}
	
}
