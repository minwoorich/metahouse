package com.multi.metahouse.asset.repository.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multi.metahouse.domain.dto.asset.AssetDTO;
import com.multi.metahouse.domain.entity.asset.AssetEntity;


@Repository
public interface AssetRepositry extends JpaRepository<AssetEntity, String>{

}
