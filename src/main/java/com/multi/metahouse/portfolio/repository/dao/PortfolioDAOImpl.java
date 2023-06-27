package com.multi.metahouse.portfolio.repository.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Repository;

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
import com.multi.metahouse.domain.entity.user.User;
import com.multi.metahouse.portfolio.repository.jpa.PortfolioAttachFileRepository;
import com.multi.metahouse.portfolio.repository.jpa.PortfolioContentImgRepository;
import com.multi.metahouse.portfolio.repository.jpa.PortfolioPointImgRepository;
import com.multi.metahouse.portfolio.repository.jpa.PortfolioRepository;
import com.multi.metahouse.portfolio.repository.jpa.PortfolioStyleImgRepository;

@Repository
public class PortfolioDAOImpl implements PortfolioDAO {
	SqlSession sqlSession;
	PortfolioRepository portfolioRepository;
	PortfolioAttachFileRepository attachFileRepository;
	PortfolioContentImgRepository contentImgRepository;
	PortfolioPointImgRepository pointImgRepository;
	PortfolioStyleImgRepository styleImgRepository;
	
	

	public PortfolioDAOImpl(SqlSession sqlSession, PortfolioRepository portfolioRepository,
			PortfolioAttachFileRepository attachFileRepository, PortfolioContentImgRepository contentImgRepository,
			PortfolioPointImgRepository pointImgRepository, PortfolioStyleImgRepository styleImgRepository) {
		super();
		this.sqlSession = sqlSession;
		this.portfolioRepository = portfolioRepository;
		this.attachFileRepository = attachFileRepository;
		this.contentImgRepository = contentImgRepository;
		this.pointImgRepository = pointImgRepository;
		this.styleImgRepository = styleImgRepository;
	}

	//Mybatis
	//INSERT
	@Override
	public void insert(PortfolioDTO portfolioDto) {
		sqlSession.insert("com.multi.metahouse.portfolio.insertPortfolio", portfolioDto);

	}
	
	@Override
	public String selectLastIndexId() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("com.multi.metahouse.portfolio.selectPortfolioId");
	}

	@Override
	public void insertContentImg(List<PortfolioContentImgDTO> contentImgDto) {
		sqlSession.insert("com.multi.metahouse.portfolio.insertContentImg", contentImgDto);

	}

	@Override
	public void insertPointImg(List<PortfolioPointImgDTO> pointImgDto) {
		sqlSession.insert("com.multi.metahouse.portfolio.insertPointImg", pointImgDto);

	}

	@Override
	public void insertStyleImg(List<PortfolioStyleImgDTO> styleImgDto) {
		sqlSession.insert("com.multi.metahouse.portfolio.insertStyleImg", styleImgDto);

	}

	@Override
	public void insertAttachFile(List<PortfolioAttachFileDTO> attachFileDto) {
		sqlSession.insert("com.multi.metahouse.portfolio.insertAttachFile", attachFileDto);

	}
	
	//SELECT
	@Override
	public List<PortfolioDTO> selectPortfolioList(String userId) {
		return sqlSession.selectList("com.multi.metahouse.portfolio.selectPortfolio", userId);
		
	}
	
	@Override
	public List<PortfolioDTO> selectPortfolioList(PortfolioDTO dto) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("com.multi.metahouse.portfolio.selectPortfolio2", dto);
	}
	
	//JPA
	//READ
	public Portfolio readPortfolio(String portfolioId) {
		Portfolio portfolio = portfolioRepository.findById(portfolioId).orElseThrow(() -> new RuntimeException());
		//System.out.println("id가"+ portfolioId +"인 portfolio출력: " + portfolio);
		return portfolio;
	}
	
	public List<PortfolioAttachFile> readAttachFile(String portfolioId){
		List<PortfolioAttachFile> portfolioAttachFile = attachFileRepository.findByPortfolioId(portfolioId);
		System.out.println("id가"+ portfolioId +"인 portfolioAttachFile출력: " + portfolioAttachFile);
		return portfolioAttachFile;
	}
	
	public List<PortfolioContentImg> readContentImg(String portfolioId){
		List<PortfolioContentImg> portfolioContentImg = contentImgRepository.findByPortfolioId(portfolioId);
		System.out.println("id가"+ portfolioId +"인 portfolioContentImg출력: " + portfolioContentImg);
		return portfolioContentImg;
	}
	
	public List<PortfolioPointImg> readPointImg(String portfolioId){
		List<PortfolioPointImg> portfolioPointImg = pointImgRepository.findByPortfolioId(portfolioId);
		System.out.println("id가"+ portfolioId +"인 portfolioPointImg출력: " + portfolioPointImg);
		return portfolioPointImg;
	}
	
	public List<PortfolioStyleImg> readStyleImg(String portfolioId){
		List<PortfolioStyleImg> portfolioStyleImg = styleImgRepository.findByPortfolioId(portfolioId);
		System.out.println("id가"+ portfolioId +"인 portfolioStyleImg출력: " + portfolioStyleImg);
		return portfolioStyleImg;
	}

	
	//DELETE
	@Override
	public void delete(String portfolioId) {
		portfolioRepository.deleteById(portfolioId);
		
	}

	@Override
	public void deleteAttachFile(String portfolioId) {
		attachFileRepository.deleteByPortfolioId(portfolioId);
		
	}

	@Override
	public void deleteContentImg(String portfolioId) {
		contentImgRepository.deleteByPortfolioId(portfolioId);
		
	}

	@Override
	public void deletePointImg(String portfolioId) {
		pointImgRepository.deleteByPortfolioId(portfolioId);
		
	}

	@Override
	public void deleteStyleImg(String portfolioId) {
		styleImgRepository.deleteByPortfolioId(portfolioId);
		
	}

	
	//update
	@Override
	public void updatePortfolio(PortfolioDTO portfolioDto) {
		Portfolio table = portfolioRepository.findById(portfolioDto.getPortfolio_id()).orElseThrow(() -> new RuntimeException());
		table.setCategory1(portfolioDto.getCategory1());
		table.setCategory2(portfolioDto.getCategory2());
		table.setPortfolioTitle(portfolioDto.getPortfolio_title());
		table.setPortfolioPjStartDay(portfolioDto.getPortfolio_pj_start_day());
		table.setPortfolioPjEndDay(portfolioDto.getPortfolio_pj_end_day());
		if(portfolioDto.getMain_img_store_filename() != null) {
			table.setMainImgStoreFilename(portfolioDto.getMain_img_store_filename());
		}
		table.setPortfolioPjContent(portfolioDto.getPortfolio_pj_content());
		table.setPortfolioPjPoint(portfolioDto.getPortfolio_pj_point());
		table.setPortfolioPjStyle(portfolioDto.getPortfolio_pj_style());
		table.setNop(Integer.parseInt(portfolioDto.getNop()));
		
		portfolioRepository.save(table);
	}

	@Override
	public void updateAttachFile(List<PortfolioAttachFile> attachFileList) {
		attachFileRepository.saveAll(attachFileList);
		
	}

	@Override
	public void updateContentImg(List<PortfolioContentImg> contentImgList) {
		contentImgRepository.saveAll(contentImgList);
		
	}

	@Override
	public void updatePointImg(List<PortfolioPointImg> pointImgList) {
		pointImgRepository.saveAll(pointImgList);
		
	}

	@Override
	public void updateStyleImg(List<PortfolioStyleImg> styleImgList) {
		styleImgRepository.saveAll(styleImgList);
		
	}
}
