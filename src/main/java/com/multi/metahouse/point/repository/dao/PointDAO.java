package com.multi.metahouse.point.repository.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.PageRequest;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;

public interface PointDAO {
	// 멤버의 포인트 충전하기
	public void chargePoint(User loginUser, int chargeAmount);
	// 포인트 충전내역 생성하기
	public void createChargedPointInfo(User loginUser, int chargeAmount);
	// 포인트 사용하기
	public void consumePoint(User loginUser, int consumeAmount);
	// 포인트 사용내역 생성하기
	public void createConsumedPointInfo(User loginUser, int consumeAmount, String consumeInfo);
	// 포인트 충전내역 조회하기
	public List<ChargedPointInfo> chargePointInfoList(User loginUser);
	// 포인트 사용내역 조회하기
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser);
	// MyPointDTO 조회하기
	public MyPointDTO getMyPointDTO(User loginUser);
	
	// pageRequest 얻어오기
	public PageRequest getPageRequest(String id);
	
	// 포인트 충전내역 조회하기 (페이징)
	public List<ChargedPointInfo> chargePointInfoList(User loginUser, int pageNo);
	
	// 포인트 사용내역 조회하기
	public List<ConsumedPointInfo> consumePointInfoList(User loginUser, int pageNo);
	
	// JSON Map
	Map<String, Object> chargePointInfoListJSON(User loginUser, int pageNo);
	Map<String, Object> consumePointInfoListJSON(User loginUser, int pageNo);
	
}
