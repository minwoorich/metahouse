package com.multi.metahouse.domain.dto.portfolio;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio_content_img")
public class PortfolioContentImgDTO{
	private String portfolio_pj_id;
	private String portfolio_id;
	private String pj_img_store_filename;
	private String pj_img_fileno;
}
