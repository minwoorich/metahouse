package com.multi.metahouse.point.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;
import com.multi.metahouse.domain.entity.point.ConsumedPointInfo;
import com.multi.metahouse.domain.entity.user.User;

import lombok.NonNull;

//ConsumedPointInfo Entity 를 관리하는 Repository
//기본 키는 Integer
@Repository
public interface ConsumedPointInfoRepository extends JpaRepository<ConsumedPointInfo, Integer>{
	@Query("SELECT COALESCE(SUM(cpi.consumingPoint), 0) FROM ConsumedPointInfo cpi WHERE cpi.user = :user")
	int getTotalConsumedPoint(@Param("user") User user);
	
	List<ConsumedPointInfo> findByUserId(String userId);
	
}
