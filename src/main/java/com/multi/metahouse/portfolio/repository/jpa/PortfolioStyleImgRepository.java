package com.multi.metahouse.portfolio.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;
import com.multi.metahouse.domain.entity.portfolio.PortfolioStyleImg;

public interface PortfolioStyleImgRepository extends JpaRepository<PortfolioStyleImg, String>{
	List<PortfolioStyleImg> findByPortfolioId(String portfolioId);
	void deleteByPortfolioId(String portfolioId);
}
