package com.multi.metahouse.domain.dto.portfolio;

import java.util.List;

import com.multi.metahouse.domain.entity.portfolio.Portfolio;
import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioInfoDTO {
	private Portfolio portfolio;
	private List<PortfolioAttachFile> attachFileList;
	private List<PortfolioContentImg> contentImgList;
	private List<PortfolioPointImg> pointImgList;
	private List<PortfolioStyleImg> styleImgList;
}
