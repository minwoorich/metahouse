package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_style_img")
public class PortfolioStyleImgDTO{
	private String portfolio_style_id;
	private String portfolio_id;
	private String style_img_store_filename;
	private String style_img_fileno;
}
