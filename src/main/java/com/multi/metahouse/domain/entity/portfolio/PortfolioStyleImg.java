package com.multi.metahouse.domain.entity.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_style_img")
public class PortfolioStyleImg{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String portfolioStyleId;
	private String portfolioId;
	private String styleImgStoreFilename;
	private String styleImgFileno;
}
