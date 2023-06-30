package com.multi.metahouse.domain.entity.asset;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_detail_img")
public class AssetDetailImgEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String assetDetailImgId;
	//private String assetId;
	private String assetDetailImgStoreFilename;
	private String assetFileno;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetEntity assetId;

}
