package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_content_img")
public class PortfolioContentImgDTO {
	private String portfolioPjId;
	private String portfolioId;
	private String pjImgStoreFilename;
	private String pjImgFileno;
}
