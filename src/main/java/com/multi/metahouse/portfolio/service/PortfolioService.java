package com.multi.metahouse.portfolio.service;

import java.util.List;

import com.multi.metahouse.domain.dto.portfolio.PortfolioAttachFileDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioInfoDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioStyleImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioUpdateDTO;
import com.multi.metahouse.domain.entity.portfolio.Portfolio;

public interface PortfolioService {
	void insert(PortfolioDTO portfolioDto, List<PortfolioContentImgDTO> contentImgDto, List<PortfolioPointImgDTO> pointImgDto, 
			List<PortfolioStyleImgDTO> styleImgDto, List<PortfolioAttachFileDTO> attachFileDto);
	
	PortfolioInfoDTO read(String portfolioId);
	void delete(String portfolioId);
	
	//profile에 출력할 portfolio List를 가져오는 메소드.
	List<PortfolioDTO> selectPortfolioList(String userId);
	List<PortfolioDTO> selectPortfolioList(PortfolioDTO dto);
	
	void update(PortfolioUpdateDTO portfolioUpdateDTO);
}
