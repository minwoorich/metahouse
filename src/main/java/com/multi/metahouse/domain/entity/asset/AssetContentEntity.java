package com.multi.metahouse.domain.entity.asset;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "asset_content")
public class AssetContentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String assetContentId;
	//private String assetId;
	private String assetStoreFilename;
	private String assetFileno;
	
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "asset_id")
	private AssetEntity assetId;

}
