package com.multi.metahouse.point.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.user.User;

// ChargedPointInfo Entity 를 관리하는 Repository
// 기본 키는 Integer
@Repository
public interface ChargedPointInfoRepository extends JpaRepository<ChargedPointInfo, Integer>{
	@Query("SELECT SUM(cpi.chargingPoint) FROM ChargedPointInfo cpi WHERE cpi.user = :user")
	Double getTotalChargingPoint(@Param("user") User user);
}
