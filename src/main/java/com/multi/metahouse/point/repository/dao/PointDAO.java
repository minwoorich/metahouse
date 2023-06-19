package com.multi.metahouse.point.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;

public interface PointDAO {
	// 포인트 충전하기
	public void chargePoint(int chargeAmount);
	// 포인트 사용하기
	public void consumePoint(int consumeAmount);
	// 포인트 충전내역 조회하기
	public List<ChargedPointInfo> chargePointInfoList(String userId);
	// 포인트 사용내역 조회하기
	public List<ConsumedPointInfo> consumePointInfoList(String userId);
}
