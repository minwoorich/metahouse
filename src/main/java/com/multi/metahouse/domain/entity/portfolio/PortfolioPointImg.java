package com.multi.metahouse.domain.entity.portfolio;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_point_img")
public class PortfolioPointImg{
	@Id
	@NonNull
	private String portfolioPointId;
	private String portfolioId;
	private String pointImgStoreFilename;
	private String pointImgFileno;
}
