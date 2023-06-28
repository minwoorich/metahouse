package com.multi.metahouse.portfolio.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.metahouse.domain.dto.asset.AssetContentDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioAttachFileDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioContentImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioInfoDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioPointImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioStyleImgDTO;
import com.multi.metahouse.domain.dto.portfolio.PortfolioUpdateDTO;
import com.multi.metahouse.domain.entity.portfolio.Portfolio;
import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;
import com.multi.metahouse.portfolio.repository.dao.PortfolioDAO;

@Service
public class PortfolioServiceImpl implements PortfolioService {
	PortfolioDAO dao;

	@Autowired
	public PortfolioServiceImpl(PortfolioDAO dao) {
		super();
		this.dao = dao;
	}

	//INSERT
	@Override
	@Transactional
	public void insert(PortfolioDTO portfolioDto, List<PortfolioContentImgDTO> contentImgDto,
			List<PortfolioPointImgDTO> pointImgDto, List<PortfolioStyleImgDTO> styleImgDto, List<PortfolioAttachFileDTO> attachFileDto) {
		dao.insert(portfolioDto);
		String portfolioLastIndexId = dao.selectLastIndexId();
		
		for (int i = 0; i < contentImgDto.size(); i++) {
			PortfolioContentImgDTO dto = contentImgDto.get(i);

			dto.setPortfolio_id(portfolioLastIndexId);	
		}
		dao.insertContentImg(contentImgDto);
		
		for (int i = 0; i < pointImgDto.size(); i++) {
			PortfolioPointImgDTO dto = pointImgDto.get(i);

			dto.setPortfolio_id(portfolioLastIndexId);		
		}
		dao.insertPointImg(pointImgDto);
		
		for (int i = 0; i < styleImgDto.size(); i++) {
			PortfolioStyleImgDTO dto = styleImgDto.get(i);

			dto.setPortfolio_id(portfolioLastIndexId);		
		}
		dao.insertStyleImg(styleImgDto);
		
		for (int i = 0; i < attachFileDto.size(); i++) {
			PortfolioAttachFileDTO dto = attachFileDto.get(i);

			dto.setPortfolio_id(portfolioLastIndexId);		
		}
		dao.insertAttachFile(attachFileDto);

	}

	//READ
	//select라 transaction필요 X
	@Override
	public PortfolioInfoDTO read(String portfolioId) {
		PortfolioInfoDTO portfolioInfo = new PortfolioInfoDTO();
		portfolioInfo.setPortfolio(dao.readPortfolio(portfolioId));
		portfolioInfo.setAttachFileList(dao.readAttachFile(portfolioId));
		portfolioInfo.setContentImgList(dao.readContentImg(portfolioId));
		portfolioInfo.setPointImgList(dao.readPointImg(portfolioId));
		portfolioInfo.setStyleImgList(dao.readStyleImg(portfolioId));
		return portfolioInfo;
	}

	@Override
	@Transactional
	public void delete(String portfolioId) {
		dao.deleteAttachFile(portfolioId);
		dao.deleteContentImg(portfolioId);
		dao.deletePointImg(portfolioId);
		dao.deleteStyleImg(portfolioId);
		dao.delete(portfolioId);
	}

	@Override
	public List<PortfolioDTO> selectPortfolioList(String userId) {
		return dao.selectPortfolioList(userId);
	}

	@Override
	public List<PortfolioDTO> selectPortfolioList(PortfolioDTO dto) {
		return dao.selectPortfolioList(dto);
	}

	@Override
	@Transactional
	public void update(PortfolioUpdateDTO portfolioUpdateDTO) {
		
		String portfolioId = portfolioUpdateDTO.getPortfolioDto().getPortfolio_id();
		
		List<PortfolioContentImg> contentImgList = portfolioUpdateDTO.getContentImgList();
		List<PortfolioPointImg> pointImgList = portfolioUpdateDTO.getPointImgList();
		List<PortfolioStyleImg> styleImgList = portfolioUpdateDTO.getStyleImgList();
		List<PortfolioAttachFile> attachFileList = portfolioUpdateDTO.getAttachFileList();

		dao.deleteContentImg(portfolioId);
		dao.deletePointImg(portfolioId);
		dao.deleteStyleImg(portfolioId);
		dao.deleteAttachFile(portfolioId);

		for(int i=0; i<contentImgList.size(); i++) {
			contentImgList.get(i).setPortfolioId(portfolioId);
		}
		
		for(int i=0; i<pointImgList.size(); i++) {
			pointImgList.get(i).setPortfolioId(portfolioId);
		}
		
		for(int i=0; i<styleImgList.size(); i++) {
			styleImgList.get(i).setPortfolioId(portfolioId);
		}
		if(attachFileList != null) {
			for(int i=0; i<attachFileList.size(); i++) {
				attachFileList.get(i).setPortfolioId(portfolioId);
			}
		}
		
		dao.updatePortfolio(portfolioUpdateDTO.getPortfolioDto());
		dao.updateContentImg(contentImgList);
		dao.updatePointImg(pointImgList);
		dao.updateStyleImg(styleImgList);
		
		if(attachFileList != null) {
			dao.updateAttachFile(attachFileList);
		}

 	}
}
