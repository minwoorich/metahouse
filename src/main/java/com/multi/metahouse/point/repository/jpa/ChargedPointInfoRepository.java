package com.multi.metahouse.point.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.entity.point.ChargedPointInfo;

// ChargedPointInfo Entity 를 관리하는 Repository
// 기본 키는 Integer
@Repository
public interface ChargedPointInfoRepository extends JpaRepository<ChargedPointInfo, Integer>{
	
}
