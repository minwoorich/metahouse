package com.multi.metahouse.portfolio.repository.dao;

import java.util.List;

import com.multi.metahouse.domain.dto.portfolio.PortfolioAttachFileDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioStyleImgDTO;
import com.multi.metahouse.domain.entity.portfolio.Portfolio;
import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;

public interface PortfolioDAO {
	void insert(PortfolioDTO portfolioDto);
	String selectLastIndexId();
	void insertContentImg(List<PortfolioContentImgDTO> contentImgDto);
	void insertPointImg(List<PortfolioPointImgDTO> pointImgDto);
	void insertStyleImg(List<PortfolioStyleImgDTO> styleImgDto);
	void insertAttachFile(List<PortfolioAttachFileDTO> attachFileDto);
	
	Portfolio readPortfolio(String portfolioId);
	List<PortfolioAttachFile> readAttachFile(String portfolioId);
	List<PortfolioContentImg> readContentImg(String portfolioId);
	List<PortfolioPointImg> readPointImg(String portfolioId);
	List<PortfolioStyleImg> readStyleImg(String portfolioId);
	
	void delete(String portfolioId);
	void deleteAttachFile(String portfolioId);
	void deleteContentImg(String portfolioId);
	void deletePointImg(String portfolioId);
	void deleteStyleImg(String portfolioId);
	
	//profile에 출력할 portfolio List를 가져오는 메소드.
	List<PortfolioDTO> selectPortfolioList(String userId);
	List<PortfolioDTO> selectPortfolioList(PortfolioDTO dto);
	
	void updatePortfolio(PortfolioDTO portfolioDto);
	void updateAttachFile(List<PortfolioAttachFile> attachFileList);
	void updateContentImg(List<PortfolioContentImg> contentImgList);
	void updatePointImg(List<PortfolioPointImg> pointImgList);
	void updateStyleImg(List<PortfolioStyleImg> styleImgList);
}
