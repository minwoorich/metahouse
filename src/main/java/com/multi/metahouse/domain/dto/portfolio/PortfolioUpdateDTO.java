package com.multi.metahouse.domain.dto.portfolio;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioUpdateDTO {
	private PortfolioDTO portfolioDto;
	private List<PortfolioContentImg> contentImgList;
	private List<PortfolioPointImg> pointImgList;
	private List<PortfolioStyleImg> styleImgList;
	private List<PortfolioAttachFile> attachFileList;
}
