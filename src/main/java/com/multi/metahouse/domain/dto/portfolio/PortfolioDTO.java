package com.multi.metahouse.domain.dto.portfolio;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.multi.metahouse.domain.dto.user.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("portfolio")
public class PortfolioDTO{
	private String portfolio_id;
	private String user_id;
	private String category1;
	private String category2;
	private String portfolio_title;
	private String portfolio_pj_start_day;
	private String portfolio_pj_end_day;
	private String main_img_store_filename;
	private String portfolio_pj_content;
	private String portfolio_pj_point;
	private String portfolio_pj_style;
	private Date portfolio_write_day;
	private String nop;
	
	private List<MultipartFile> portfolioPjContentImg;
	private List<MultipartFile> portfolioPjPointImg;
	private List<MultipartFile> portfolioPjStyleImg;
	private List<MultipartFile> portfolioAttachFile;
	private MultipartFile multipartMainImg;
	
	private String delContentImg;
	private String delPointImg;
	private String delStyleImg;
	
	private int contentSize;
	private int pointSize;
	private int styleSize;
	
	

}
