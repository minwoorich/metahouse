package com.multi.metahouse.portfolio.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;

public interface PortfolioContentImgRepository extends JpaRepository<PortfolioContentImg, String>{
	List<PortfolioContentImg> findByPortfolioId(String portfolioId);
	void deleteByPortfolioId(String portfolioId);
	void deleteByPortfolioIdAndPjImgFileno(String portfolioId, String pjImgFileno);
}
