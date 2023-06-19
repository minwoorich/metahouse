package com.multi.metahouse.domain.entity.asset;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_detail_img")
public class AssetDetailImgEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String assetDetailImgId;
	private String assetId;
	private String assetDetailImgStoreFilename;
	private String assetFileno;

}
