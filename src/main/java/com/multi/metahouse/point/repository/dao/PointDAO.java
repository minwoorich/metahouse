package com.multi.metahouse.point.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.point.MyPointDTO;
import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;

public interface PointDAO {
	// 멤버의 포인트 충전하기
	public void chargePoint(User loginUser, int chargeAmount);
	// 포인트 충전내역 생성하기
	public void createPointInfo(User loginUser, int chargeAmount);
	// 포인트 사용하기
	public void consumePoint(User loginUser, int consumeAmount);
	// 포인트 충전내역 조회하기
	public List<ChargedPointInfo> chargePointInfoList(String userId);
	// 포인트 사용내역 조회하기
	public List<ConsumedPointInfo> consumePointInfoList(String userId);
	// MyPointDTO 조회하기
	public MyPointDTO getMyPointDTO(User loginUser);
}
