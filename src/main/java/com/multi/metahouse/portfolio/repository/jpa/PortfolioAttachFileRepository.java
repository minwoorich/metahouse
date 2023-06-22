package com.multi.metahouse.portfolio.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.portfolio.Portfolio;
import com.multi.metahouse.domain.entity.portfolio.PortfolioAttachFile;
import com.multi.metahouse.domain.entity.portfolio.PortfolioContentImg;

public interface PortfolioAttachFileRepository extends JpaRepository<PortfolioAttachFile, String>{
	List<PortfolioAttachFile> findByPortfolioId(String portfolioId);
	void deleteByPortfolioId(String portfolioId);
}
