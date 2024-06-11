package com.multi.metahouse.portfolio.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioPointImg;

public interface PortfolioPointImgRepository extends JpaRepository<PortfolioPointImg, String>{
	List<PortfolioPointImg> findByPortfolioId(String portfolioId);
	void deleteByPortfolioId(String portfolioId);
	void deleteByPortfolioIdAndPointImgFileno(String portfolioId, String pointImgFileno);
}
