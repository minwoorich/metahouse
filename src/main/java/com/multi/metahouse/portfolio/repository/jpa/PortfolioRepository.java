package com.multi.metahouse.portfolio.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.metahouse.domain.entity.portfolio.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, String>{
}
