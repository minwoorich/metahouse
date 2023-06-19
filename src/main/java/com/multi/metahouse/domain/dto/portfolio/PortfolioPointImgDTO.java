package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_point_img")
public class PortfolioPointImgDTO {
	private String portfolioPointId;
	private String portfolioId;
	private String pointImgStoreFilename;
	private String pointImgFileno;
}
