package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_style_img")
public class PortfolioStyleImgDTO {
	private String portfolioStyleId;
	private String portfolioId;
	private String styleImgStoreFilename;
	private String styleImgFileno;
}
