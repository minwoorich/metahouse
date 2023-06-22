package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_point_img")
public class PortfolioPointImgDTO{
	private String portfolio_point_id;
	private String portfolio_id;
	private String point_img_store_filename;
	private String point_img_fileno;
}
