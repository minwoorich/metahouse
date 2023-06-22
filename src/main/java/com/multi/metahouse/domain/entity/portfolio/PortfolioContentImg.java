package com.multi.metahouse.domain.entity.portfolio;

import javax.persistence.Entity;
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
@Table(name = "portfolio_content_img")
public class PortfolioContentImg{
	@Id
	@NonNull
	private String portfolioPjId;
	private String portfolioId;
	private String pjImgStoreFilename;
	private String pjImgFileno;
}
